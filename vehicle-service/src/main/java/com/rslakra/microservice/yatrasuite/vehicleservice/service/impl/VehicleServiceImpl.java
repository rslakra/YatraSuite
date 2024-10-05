package com.rslakra.microservice.yatrasuite.vehicleservice.service.impl;

import com.rslakra.appsuite.core.BeanUtils;
import com.rslakra.appsuite.spring.exception.NoRecordFoundException;
import com.rslakra.appsuite.spring.filter.Filter;
import com.rslakra.appsuite.spring.persistence.ServiceOperation;
import com.rslakra.appsuite.spring.service.AbstractServiceImpl;
import com.rslakra.microservice.yatrasuite.common.Constants;
import com.rslakra.microservice.yatrasuite.common.exception.InvalidVehicleStateException;
import com.rslakra.microservice.yatrasuite.common.exception.NotFoundException;
import com.rslakra.microservice.yatrasuite.vehicleservice.dto.VehicleDetailDTO;
import com.rslakra.microservice.yatrasuite.vehicleservice.persistence.entity.LocationHistory;
import com.rslakra.microservice.yatrasuite.vehicleservice.persistence.entity.Vehicle;
import com.rslakra.microservice.yatrasuite.vehicleservice.persistence.entity.VehicleWithLocation;
import com.rslakra.microservice.yatrasuite.vehicleservice.persistence.repository.LocationHistoryRepository;
import com.rslakra.microservice.yatrasuite.vehicleservice.persistence.repository.VehicleRepository;
import com.rslakra.microservice.yatrasuite.vehicleservice.persistence.repository.VehicleWithLocationRepository;
import com.rslakra.microservice.yatrasuite.vehicleservice.service.VehicleService;
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
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Implementation of the Vehicle Service Interface
 */

@Service
public class VehicleServiceImpl extends AbstractServiceImpl<Vehicle, UUID> implements VehicleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleServiceImpl.class);
    private VehicleRepository vehicleRepository;
    private VehicleWithLocationRepository vehicleWithLocationRepository;
    private LocationHistoryRepository locationHistoryRepository;

    /**
     * @param vehicleRepository
     * @param vehicleWithLocationRepository
     * @param locationHistoryRepository
     */
    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository,
                              VehicleWithLocationRepository vehicleWithLocationRepository,
                              LocationHistoryRepository locationHistoryRepository) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleWithLocationRepository = vehicleWithLocationRepository;
        this.locationHistoryRepository = locationHistoryRepository;
    }

    /**
     * @param operation
     * @param vehicle
     * @return
     */
    @Override
    public Vehicle validate(ServiceOperation operation, Vehicle vehicle) {
        return null;
    }

    /**
     * @param vehicle
     * @return
     */
    @Override
    public Vehicle create(Vehicle vehicle) {
        return null;
    }

    /**
     * @param vehicles
     * @return
     */
    @Override
    public List<Vehicle> create(List<Vehicle> vehicles) {
        return null;
    }

    /**
     * @return
     */
    @Override
    public List<Vehicle> getAll() {
        return vehicleRepository.findAll();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Vehicle getById(final UUID id) {
        return vehicleRepository.findById(id).orElseThrow(() -> new NoRecordFoundException("id:%s", id));
    }

    /**
     * @param filter
     * @return
     */
    @Override
    public List<Vehicle> getByFilter(Filter filter) {
        return vehicleRepository.findAll();
    }

    /**
     * @param filter
     * @param pageable
     * @return
     */
    @Override
    public Page<Vehicle> getByFilter(Filter filter, Pageable pageable) {
        return null;
    }

    /**
     * @param vehicle
     * @return
     */
    @Override
    public Vehicle update(Vehicle vehicle) {
        return null;
    }

    /**
     * @param vehicles
     * @return
     */
    @Override
    public List<Vehicle> update(List<Vehicle> vehicles) {
        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Vehicle delete(UUID id) {
        return null;
    }

    /**
     * Adds a new vehicle (with its current location).
     *
     * @param latitude         the current latitude of the vehicle
     * @param longitude        the current longitude of the vehicle
     * @param batteryLevel     the current battery level of the vehicle
     * @param vehicleDetailDTO DTO holding the vehicle info (individual fields that will be converted to JSON)
     * @return the entity object representing the vehicle
     */
    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Vehicle addVehicle(BigDecimal latitude, BigDecimal longitude, int batteryLevel,
                              VehicleDetailDTO vehicleDetailDTO) {
        LOGGER.debug("+addVehicle({}, {}, {}, {})", latitude, longitude, batteryLevel, vehicleDetailDTO);
        Vehicle vehicle = new Vehicle();
        vehicle.setId(UUID.randomUUID());
        vehicle.setSerialNumber(vehicleDetailDTO.getPurchaseInfo().getSerialNumber());
        vehicle.setBatteryLevel(batteryLevel);
//        vehicle.setVehicleDetail(vehicleDetailDTO.toVehicleInfo());
        vehicle.setVehicleInfo(vehicleDetailDTO.toVehicleInfo());
        vehicle.addLocationHistory(createLocationHistory(vehicle, latitude, longitude));
        LOGGER.debug("vehicle:{}", vehicle);
        vehicle = vehicleRepository.save(vehicle);
        LOGGER.debug("-addVehicle(), vehicle:{}", vehicle);
        return vehicle;
    }

    /**
     * Gets all vehicles w/ their current location (up to the limit requested).
     *
     * @param maxRecords the maximum number of vehicles to return (null defaults to MAX_VEHICLES_TO_RETURN)
     * @return a list of vehicle entity objects
     */
    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE, readOnly = true)
    public List<VehicleWithLocation> getVehiclesWithLocation(Integer maxRecords) {
        int max = maxRecords == null ? MAX_VEHICLES_TO_RETURN : maxRecords;
        return vehicleWithLocationRepository.getVehiclesWithLocation(max);
    }

    /**
     * Gets all vehicles w/ their current location (up to the limit requested).
     *
     * @param maxRecords the maximum number of vehicles to return (null defaults to MAX_VEHICLES_TO_RETURN)
     * @return a list of vehicle entity objects
     */
    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE, readOnly = true)
    public List<Vehicle> getVehicles(Integer maxRecords) {
        int max = maxRecords == null ? MAX_VEHICLES_TO_RETURN : maxRecords;
        List<Vehicle> vehicles = vehicleRepository.findAll();
        if (BeanUtils.isNotEmpty(vehicles)) {
            List<LocationHistory> locationHistories = locationHistoryRepository.findAll();
            vehicles.stream().forEach(vehicle -> {
                List<LocationHistory> locationHistoryList = locationHistories.stream()
                    .filter(locationHistory -> locationHistory.getVehicle().getId().equals(vehicle.getId()))
                    .collect(Collectors.toList());
                vehicle.setLocationHistories(locationHistoryList);
            });
        }

        return vehicles;
    }

    /**
     * @param vehicleId
     * @return
     */
    public List<LocationHistory> getLocationHistory(UUID vehicleId) {
        return locationHistoryRepository.findAllByVehicleId(vehicleId);
    }

    /**
     * @param vehicle
     */
    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE, readOnly = true)
    public void fillLocationHistory(Vehicle vehicle) {
        List<LocationHistory> locationHistories = getLocationHistory(vehicle.getId());
        vehicle.setLocationHistories(locationHistories);
    }

    /**
     * @param vehicleId
     * @param withLocationHistory
     * @return
     * @throws NotFoundException
     */
    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE, readOnly = true)
    public Vehicle getVehicle(UUID vehicleId, Boolean withLocationHistory) throws NotFoundException {
        LOGGER.debug("+getVehicle({}, {})", vehicleId, withLocationHistory);
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(vehicleId);
        if (!vehicleOptional.isPresent()) {
            LOGGER.error("Vehicle not found with vehicleId:{}", vehicleId);
            throw new NotFoundException(String.format(Constants.ERR_VEHICLE_NOT_FOUND, vehicleId.toString()));
        }

        Vehicle vehicle = vehicleOptional.get();
        if (Boolean.TRUE.equals(withLocationHistory)) {
            fillLocationHistory(vehicle);
        }

        LOGGER.debug("-getVehicle(), vehicle: {}", vehicle);
        return vehicle;
    }

    /**
     * Gets a specific vehicle.
     *
     * @param vehicleId the UUID of the vehicle to retrieve
     * @return entity object representing the vehicle
     * @throws NotFoundException if the vehicle id does not exist in the database
     */
    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE, readOnly = true)
    public Vehicle getVehicle(UUID vehicleId) throws NotFoundException {
        return getVehicle(vehicleId, false);
    }

    /**
     * Marks a vehicle as NOT "in use", and updates its new location and battery level (typically called after a ride is
     * ended).
     * <p>
     * From the supplier’s perspective, check-in is an instance when the renter returns the rental vehicle to the
     * supplier at the end of the rental period. In other words, check-in meaning is the same as car rental return or
     * collection.
     * <p>
     * It is important to note that this is car rental supplier specific terminology. Check-in can have a different
     * meaning in other travel sectors.
     *
     * @param vehicleId    the UUID of the vehicle to mark as no longer "in use"
     * @param latitude     the latitude of the new location of the vehicle
     * @param longitude    the latitude of the new location of the vehicle
     * @param batteryLevel the new battery level
     * @param endTime
     * @throws NotFoundException            if the vehicle id does not exist in the database
     * @throws InvalidVehicleStateException if the vehicle is not currently marked "in use"
     */
    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Vehicle checkinVehicle(UUID vehicleId, BigDecimal latitude, BigDecimal longitude, Integer batteryLevel,
                                  LocalDateTime endTime)
        throws NotFoundException {
        Vehicle vehicle = getVehicle(vehicleId);
        addLocationHistory(vehicle, latitude, longitude);

        if (vehicle.getLastRideEnd() == null || endTime.isAfter(vehicle.getLastRideEnd())) {
            vehicle.setLastRideEnd(endTime);
        }

        vehicle.setBatteryLevel(batteryLevel);
        vehicle = vehicleRepository.save(vehicle);

        return vehicle;
    }

    /**
     * Marks a vehicle as "in use" (typically called at the beginning of a ride).
     * <p>
     * From the supplier’s perspective, check-out is an instance when the renter receives the rental vehicle from the
     * supplier in the beginning of the rental period. In other words, check-out meaning is the same as pick-up or
     * delivery.
     * <p>
     * It is important to note that this is car rental supplier specific terminology. Check-out can have a different
     * meaning in other travel sectors.
     *
     * @param vehicleId the UUID of the vehicle to mark as "in use"
     * @param leftAt    the UUID of the vehicle to mark as "in use"
     * @return an entity object representing the vehicle
     * @throws NotFoundException            if the vehicle id does not exist in the database
     * @throws InvalidVehicleStateException if the vehicle is already marked "in use"
     */
    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Vehicle checkoutVehicle(UUID vehicleId, LocalDateTime leftAt) throws NotFoundException {
        Vehicle vehicle = getVehicle(vehicleId);
        if (vehicle.getLastRideStart() == null || leftAt.isAfter(vehicle.getLastRideStart())) {
            vehicle.setLastRideStart(leftAt);
        }

        vehicle = vehicleRepository.save(vehicle);
        return vehicle;
    }

    /**
     * Removes the specified vehicle.
     *
     * @param vehicleId the UUID of the vehicle to delete
     * @throws NotFoundException if the vehicle id does not exist in the database
     */
    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void removeVehicle(UUID vehicleId) throws NotFoundException, InvalidVehicleStateException {
        Vehicle vehicle = getVehicle(vehicleId);
        if (vehicle.isInUse()) {
            throw new InvalidVehicleStateException(String.format(Constants.ERR_VEHICLE_IN_USE, vehicleId.toString()));
        } else {
            vehicleRepository.delete(vehicle);
        }
    }

    /**
     * @param vehicle
     * @param latitude
     * @param longitude
     * @return
     */
    private LocationHistory createLocationHistory(Vehicle vehicle, BigDecimal latitude, BigDecimal longitude) {
        LocationHistory locationHistory = new LocationHistory();
        locationHistory.setVehicle(vehicle);
        locationHistory.setLatitude(latitude);
        locationHistory.setLongitude(longitude);

        return locationHistory;
    }

    /**
     * Adds a new location history record for a vehicle
     *
     * @param vehicle   the UUID of the vehicle
     * @param latitude  the latitude of the location of the vehicle
     * @param longitude the latitude of the location of the vehicle
     */
    private void addLocationHistory(Vehicle vehicle, BigDecimal latitude, BigDecimal longitude) {
        LocationHistory locationHistory = createLocationHistory(vehicle, latitude, longitude);
        locationHistory = locationHistoryRepository.save(locationHistory);
    }
}
