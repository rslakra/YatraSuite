package com.rslakra.microservice.yatrasuite.yatrathymeleaf.service.impl;

import com.devamatre.appsuite.core.BeanUtils;
import com.devamatre.appsuite.core.Payload;
import com.devamatre.appsuite.spring.client.ApiRestClient;
import com.devamatre.appsuite.spring.exception.InvalidRequestException;
import com.devamatre.appsuite.spring.persistence.Operation;
import com.rslakra.microservice.yatrasuite.yatrathymeleaf.config.RideServiceConfig;
import com.rslakra.microservice.yatrasuite.yatrathymeleaf.dto.ride.Ride;
import com.rslakra.microservice.yatrasuite.yatrathymeleaf.framework.ui.service.impl.AbstractClientServiceImpl;
import com.rslakra.microservice.yatrasuite.yatrathymeleaf.service.RideService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Rohtash Lakra
 * @created 10/9/21 5:50 PM
 */
@Service
public class RideServiceImpl extends AbstractClientServiceImpl<Ride, Long> implements RideService {

    // apiRestClient
    private static final Logger LOGGER = LoggerFactory.getLogger(RideServiceImpl.class);

    private final RideServiceConfig serviceConfig;

    /**
     * @param serviceConfig
     */
    @Autowired
    public RideServiceImpl(final RideServiceConfig serviceConfig) {
        super(new ApiRestClient(serviceConfig.getApiUrl(null)));
        LOGGER.debug("RideServiceImpl({})", serviceConfig);
        this.serviceConfig = serviceConfig;
    }

    /**
     * Validates the <code>T</code> object.
     *
     * @param operation
     * @param ride
     * @return
     */
    @Override
    public Ride validate(Operation operation, Ride ride) {
        LOGGER.debug("+validate({}, {})", operation, ride);

        switch (operation) {
            case CREATE:
                if (BeanUtils.isEmpty(ride.getVehicleId())) {
                    throw new InvalidRequestException("The ride's vehicle ID should provide!");
                }

                break;

            case UPDATE:
                if (BeanUtils.isNull(ride.getId())) {
                    throw new InvalidRequestException("The ride's ID should provide!");
                }

                break;

            default:
                throw new InvalidRequestException("Unsupported Operation!");
        }

        LOGGER.debug("-validate(), ride:{}", ride);
        return ride;
    }

    /**
     * @param ride
     * @return
     */
    @Override
    public Ride create(Ride ride) {
        LOGGER.debug("+create({})", ride);
        if (BeanUtils.isNull(ride)) {
            throw new InvalidRequestException("The ride should provide!");
        }

        validate(Operation.CREATE, ride);
        ride = getRestClient().doPost(RIDES, ride, Ride.class);
        LOGGER.debug("-create(), ride:{}", ride);
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
            throw new InvalidRequestException("The users should provide!");
        }

        rides.forEach(ride -> validate(Operation.CREATE, ride));
        rides = getRestClient().doPost(RIDES_BATCH, rides, List.class);
        LOGGER.debug("-create(), rides:{}", rides);
        return rides;
    }

    /**
     * @return
     */
    @Override
    public List<Ride> getAll() {
        LOGGER.debug("+getAll()");
        // note: get results by array and convert to list.
        List<Ride> rides;
        // helps to display empty ui page.
        try {
            rides = Arrays.asList(getRestClient().doGet(RIDES, Ride[].class));
        } catch (Exception ex) {
            LOGGER.error(ex.getLocalizedMessage(), ex);
            // helps to display empty ui page.
            rides = new ArrayList<>();
        }

        LOGGER.debug("-getAll(), rides:{}", rides);
        return rides;
    }

    /**
     * @param filters
     * @return
     */
    @Override
    public List<Ride> getByFilter(Map<String, Object> filters) {
        LOGGER.debug("+getByFilter({})", filters);
        // rest/rides/filter?id=3
        final Ride[] rides = getRestClient().doGet(RIDES_FILTER, Ride[].class, filters);
        LOGGER.debug("-getByFilter(), rides:{}", rides);
        return Arrays.asList(rides);
    }

    /**
     * @param filters
     * @param pageable
     * @return
     */
    @Override
    public List<Ride> getByFilter(Map<String, Object> filters, Pageable pageable) {
        LOGGER.debug("+getByFilter({}, {})", filters, pageable);
        List<Ride> rides = getByFilter(filters);
        LOGGER.debug("-getByFilter(), rides:{}", rides);
        return rides;
    }

    /**
     * Returns the <code>T</code> object by <code>id</code>.
     *
     * @param id
     * @return
     */
    @Override
    public Ride getById(Long id) {
        LOGGER.debug("+getById({})", id);
        // rest/rides/filter?id=16
        Ride ride = (Ride) getByFilter(Payload.newBuilder().ofPair("id", id)).stream().findFirst().orElse(null);
        LOGGER.debug("-getById(), ride:{}", ride);
        return ride;
    }

    /**
     * Returns the list of rides by the email address.
     *
     * @param email
     * @return
     */
    @Override
    public List<Ride> getRidesByEmail(String email) {
        LOGGER.debug("+getRidesByEmail({})", email);
        BeanUtils.assertNonNull(email, "The ride's email should provide!");
        List<Ride> rides = getByFilter(Payload.newBuilder().ofPair("email", email));
        LOGGER.debug("-getRidesByEmail(), rides:{}", rides);
        return rides;
    }

    /**
     * Returns the list of rides by status.
     *
     * @param status
     * @return
     */
    @Override
    public List<Ride> getByStatus(String status) {
        LOGGER.debug("+getByStatus({})", status);
        BeanUtils.assertNonNull(status, "The ride status should provide!");
        List<Ride> rides = getByFilter(Payload.newBuilder().ofPair("status", status));
        LOGGER.debug("-getByStatus(), rides: {}", rides);
        return rides;
    }


    /**
     * @param ride
     * @return
     */
    @Override
    public Ride update(Ride ride) {
        LOGGER.debug("+update({})", ride);
        if (BeanUtils.isEmpty(ride)) {
            throw new InvalidRequestException("The ride should provide!");
        }

        validate(Operation.UPDATE, ride);
        getRestClient().doPut(RIDES, ride, Ride.class);

        LOGGER.debug("-update(), ride:{}", ride);
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
        getRestClient().doPut(RIDES_BATCH, rides, List.class);

        LOGGER.debug("-update(), rides:{}", rides);
        return rides;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Ride delete(Long id) {
        LOGGER.debug("+delete({})", id);
        BeanUtils.assertNonNull(id, "The ride's id should provide!");
        Ride ride = null;
        getRestClient().doDelete(RIDE_BY_ID, Payload.newBuilder().ofPair("id", id));
        LOGGER.debug("-delete(), ride:{}", ride);
        return ride;
    }


    /**
     * @param payload
     * @return
     */
    @Override
    public Ride startRide(Payload payload) {
        LOGGER.debug("+startRide({})", payload);
        BeanUtils.assertNonNull(payload, "The payload should provide!");
        if (BeanUtils.isEmpty(payload)) {
            throw new InvalidRequestException("The ride's info should provide!");
        } else if (BeanUtils.isEmpty(payload.get(EMAIL))) {
            throw new InvalidRequestException("The ride's email should provide!");
        } else if (BeanUtils.isEmpty(payload.get(VEHICLE_ID))) {
            throw new InvalidRequestException("The ride's vehicleId should provide!");
        }

        Ride ride = getRestClient().doPost(RIDE_START, payload, Ride.class);
        LOGGER.debug("-startRide(), ride: {}", ride);
        return ride;
    }

    /**
     * @param payload
     * @return
     */
    @Override
    public Ride endRide(Payload payload) {
        LOGGER.debug("+endRide({})", payload);
        BeanUtils.assertNonNull(payload, "The payload should provide!");
        if (BeanUtils.isEmpty(payload)) {
            throw new InvalidRequestException("The ride's info should provide!");
        } else if (BeanUtils.isEmpty(payload.get(EMAIL))) {
            throw new InvalidRequestException("The ride's email should provide!");
        } else if (BeanUtils.isEmpty(payload.get(VEHICLE_ID))) {
            throw new InvalidRequestException("The ride's vehicleId should provide!");
        } else if (BeanUtils.isEmpty(payload.get("battery"))) {
            throw new InvalidRequestException("The battery level should provide!");
        } else if (BeanUtils.isEmpty(payload.get("latitude"))) {
            throw new InvalidRequestException("The latitude should provide!");
        } else if (BeanUtils.isEmpty(payload.get("longitude"))) {
            throw new InvalidRequestException("The longitude should provide!");
        }

        Ride ride = getRestClient().doPost(RIDE_END, payload, Ride.class);
        LOGGER.debug("-endRide(), ride: {}", ride);
        return ride;
    }
}
