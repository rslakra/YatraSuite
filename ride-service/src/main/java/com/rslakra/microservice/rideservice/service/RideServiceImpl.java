package com.rslakra.microservice.rideservice.service;

import com.devamatre.framework.core.BeanUtils;
import com.devamatre.framework.spring.exception.InvalidRequestException;
import com.devamatre.framework.spring.exception.NoRecordFoundException;
import com.devamatre.framework.spring.filter.Filter;
import com.devamatre.framework.spring.persistence.Operation;
import com.devamatre.framework.spring.service.AbstractServiceImpl;
import com.rslakra.microservice.common.Constants;
import com.rslakra.microservice.common.exception.InvalidVehicleStateException;
import com.rslakra.microservice.common.exception.NotFoundException;
import com.rslakra.microservice.rideservice.filter.RideFilter;
import com.rslakra.microservice.rideservice.kafka.event.EventPublisher;
import com.rslakra.microservice.rideservice.kafka.event.RideEndedEvent;
import com.rslakra.microservice.rideservice.kafka.event.RideStartedEvent;
import com.rslakra.microservice.rideservice.persistence.entity.Ride;
import com.rslakra.microservice.rideservice.persistence.repository.RideRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Rides Service.
 *
 * @author Rohtash Lakra
 * @created 7/23/23 9:16 PM
 */
@Service
public class RideServiceImpl extends AbstractServiceImpl<Ride, Long> implements RideService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RideServiceImpl.class);

    private final RideRepository rideRepository;
    private final EventPublisher eventPublisher;

    /**
     * @param rideRepository
     * @param eventPublisher
     */
    @Autowired
    public RideServiceImpl(RideRepository rideRepository, EventPublisher eventPublisher) {
        LOGGER.debug("RideServiceImpl({}, {})", rideRepository, eventPublisher);
        this.rideRepository = rideRepository;
        this.eventPublisher = eventPublisher;
    }

    /**
     * @param operation
     * @param ride
     * @return
     */
    @Override
    public Ride validate(Operation operation, Ride ride) {
        LOGGER.debug("+validate({}, {})", operation, ride);
        switch (operation) {
            case CREATE: {
                if (BeanUtils.isEmpty(ride.getVehicleId())) {
                    throw new InvalidRequestException("The ride's vehicle ID should provide!");
                } else if (BeanUtils.isEmpty(ride.getUserEmail())) {
                    throw new InvalidRequestException("The ride's userEmail should provide!");
                }
            }

            break;

            case UPDATE: {
                if (BeanUtils.isNull(ride.getId())) {
                    throw new InvalidRequestException("The ride's ID should provide!");
                }
            }

            break;

            default:
                throw new InvalidRequestException("Unsupported Operation!");
        }

        LOGGER.debug("-validate(), ride: {}", ride);
        return ride;
    }

    /**
     * @param ride
     * @return
     */
    @Override
    public Ride create(Ride ride) {
        LOGGER.debug("+create({})", ride);
        ride = validate(Operation.CREATE, ride);
        ride = rideRepository.save(ride);
        LOGGER.debug("-create(), ride: {}", ride);
        return ride;
    }

    /**
     * @param rides
     * @return
     */
    @Override
    public List<Ride> create(List<Ride> rides) {
        LOGGER.debug("+create({})", rides);
        if (BeanUtils.isEmpty(rides)) {
            throw new InvalidRequestException("The rides should provide!");
        }

        rides.forEach(ride -> validate(Operation.CREATE, ride));
        rides = rideRepository.saveAll(rides);

        LOGGER.debug("-create(), rides: {}", rides);
        return rides;
    }

    /**
     * @return
     */
    @Override
    public List<Ride> getAll() {
        LOGGER.debug("+getAll()");
        List<Ride> rides = rideRepository.findAll();
        LOGGER.debug("+getAll(), rides:{}", rides);
        return rides;
    }

    /**
     * @param filter
     * @return
     */
    @Override
    public List<Ride> getByFilter(Filter filter) {
        LOGGER.debug("+getByFilter({})", filter);
        List<Ride> rides;
        if (filter.hasKeys(RideFilter.EMAIL, RideFilter.VEHICLE_ID)) {
            UUID vehicleId = UUID.fromString(filter.getValue(RideFilter.VEHICLE_ID, String.class));
            rides = rideRepository.getActiveRides(filter.getValue(RideFilter.EMAIL, String.class), vehicleId);
        } else if (filter.hasKey(RideFilter.EMAIL)) {
            rides = rideRepository.findRidesByUser(filter.getValue(RideFilter.EMAIL, String.class));
        } else if (filter.hasKey(RideFilter.VEHICLE_ID)) {
            UUID vehicleId = UUID.fromString(filter.getValue(RideFilter.VEHICLE_ID).toString());
            rides = rideRepository.getActiveRidesByVehicle(vehicleId);
        } else {
            rides = rideRepository.findAll();
        }

        LOGGER.debug("-getByFilter(), rides: {}", rides);
        return rides;
    }

    /**
     * @param filter
     * @param pageable
     * @return
     */
    @Override
    public Page<Ride> getByFilter(Filter filter, Pageable pageable) {
        return rideRepository.findAll(pageable);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Ride getById(final Long id) {
        return rideRepository.findById(id)
            .orElseThrow(() -> new NoRecordFoundException("id:%d", id));
    }

    /**
     * @param ride
     * @return
     */
    @Override
    public Ride update(Ride ride) {
        LOGGER.debug("+update({})", ride);
        ride = validate(Operation.UPDATE, ride);
        ride = rideRepository.save(ride);
        LOGGER.debug("-update(), ride: {}", ride);
        return ride;
    }

    /**
     * @param rides
     * @return
     */
    @Override
    public List<Ride> update(List<Ride> rides) {
        LOGGER.debug("+update({})", rides);
        if (BeanUtils.isEmpty(rides)) {
            throw new InvalidRequestException("The rides should provide!");
        }

        rides.forEach(ride -> validate(Operation.UPDATE, ride));
        rides = rideRepository.saveAll(rides);

        LOGGER.debug("-update(), rides: {}", rides);
        return rides;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Ride delete(Long id) {
        LOGGER.debug("+delete({})", id);
        Objects.requireNonNull(id);
        Ride ride = getById(id);
        LOGGER.info("Deleting {}", ride);
        rideRepository.deleteById(ride.getId());
        LOGGER.debug("-delete(), ride: {}", ride);
        return ride;
    }

    /**
     * Starts a ride for this vehicle/ride combination.
     *
     * @param vehicleId the vehicle that the ride will be riding
     * @param userEmail the email address that identifies the ride
     * @param startTime the date/time that the ride is starting their ride
     * @return the Ride object representing the ride's ride
     * @throws NotFoundException if the vehicle or ride is not found
     */
    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Ride startRide(UUID vehicleId, String userEmail, LocalDateTime startTime)
        throws InvalidVehicleStateException {
        LOGGER.debug("+startRide({}, {}, {})", vehicleId, userEmail, startTime);
        List<Ride> activeRidesForVehicle = rideRepository.getActiveRidesByVehicle(vehicleId);
        if (!activeRidesForVehicle.isEmpty()) {
            throw new InvalidVehicleStateException(String.format(Constants.ERR_VEHICLE_IN_USE, vehicleId.toString()));
        }

        // create ride
        Ride ride = new Ride();
        ride.setUserEmail(userEmail);
        ride.setVehicleId(vehicleId);
        ride.setStartTime(startTime);
        ride = rideRepository.save(ride);
        LOGGER.debug("ride: {}", ride);

        // publish ride event
        RideStartedEvent rideStartedEvent = RideStartedEvent.builder()
            .rideId(ride.getId())
            .userEmail(ride.getUserEmail())
            .vehicleId(ride.getVehicleId())
            .startTime(startTime)
            .build();

        LOGGER.debug("Publishing ride event:{}", rideStartedEvent);
        eventPublisher.publish(rideStartedEvent.getType().name(), rideStartedEvent);

        LOGGER.debug("-startRide(), ride: {}", ride);
        return ride;
    }

    /**
     * Ends the active ride for this vehicle/email combination.
     *
     * @param vehicleId the vehicle that the ride will be riding
     * @param userEmail the email address that identifies the ride
     * @param endTime   the date/time the ride ended
     * @return A status message describing the distance travelled, speed, and duration
     * @throws NotFoundException if the vehicle or ride is not found //     * @throws InvalidValueException if an error
     *                           occurs during the math calculations
     */
    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Ride endRide(UUID vehicleId, String userEmail, Integer battery, BigDecimal latitude, BigDecimal longitude,
                        LocalDateTime endTime) throws NotFoundException {
        LOGGER.debug("+endRide({}, {}, {}, {}, {})", vehicleId, userEmail, battery, latitude, longitude);

        // get the active ride for this ride/vehicle
        Ride ride = getActiveRides(userEmail, vehicleId);

        // set the end time for the ride
        ride.setEndTime(endTime);
        ride = rideRepository.save(ride);
        LOGGER.debug("ride: {}", ride);

        // publish the RideEnded event
        RideEndedEvent rideEndedEvent = RideEndedEvent.builder()
            .rideId(ride.getId())
            .userEmail(ride.getUserEmail())
            .vehicleId(ride.getVehicleId())
            .battery(battery)
            .latitude(latitude)
            .longitude(longitude)
            .endTime(endTime)
            .build();

        LOGGER.debug("Publishing ride event:{}", rideEndedEvent);
        eventPublisher.publish(rideEndedEvent.getType().name(), rideEndedEvent);

        LOGGER.debug("-endRide(), ride: {}", ride);
        return ride;
    }

    /**
     * Gets all rides for the specified ride.
     *
     * @param userEmail the email address that identifies the ride
     * @return List of ride objects for this ride
     * @throws NotFoundException if the ride is not found
     */
    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE, readOnly = true)
    public List<Ride> getRidesByUser(String userEmail) {
        return rideRepository.findRidesByUser(userEmail);
    }

    /**
     * Gets a specific active ride (ride/vehicle combination).
     *
     * @param userEmail the email address that identifies the ride
     * @param vehicleId the vehicle that the ride is riding
     * @return the Ride object representing the requested ride
     * @throws NotFoundException if the vehicle or ride is not found
     */
    @Override
    public Ride getActiveRides(String userEmail, UUID vehicleId) throws NotFoundException {
        // should only be one active, but get a list just in case
        List<Ride> rideList = rideRepository.getActiveRides(userEmail, vehicleId);

        if (rideList.isEmpty()) {
            throw new NotFoundException(String.format(Constants.ERR_NO_ACTIVE_RIDE, vehicleId, userEmail));
        }

        return rideList.get(0);
    }
}
