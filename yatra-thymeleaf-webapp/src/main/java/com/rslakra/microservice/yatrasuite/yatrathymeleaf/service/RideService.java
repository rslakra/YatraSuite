package com.rslakra.microservice.yatrasuite.yatrathymeleaf.service;

import com.rslakra.appsuite.core.Payload;
import com.rslakra.microservice.yatrasuite.yatrathymeleaf.dto.ride.Ride;
import com.rslakra.microservice.yatrasuite.yatrathymeleaf.framework.ui.service.AbstractClientService;

import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 8/20/21 8:11 PM
 */
public interface RideService extends AbstractClientService<Ride, Long> {

    String RIDES = "rides";
    String RIDES_BATCH = "rides/batch";
    String RIDE_BY_ID = "rides/{id}";
    String RIDES_FILTER = "rides/filter";
    String RIDE_ACTIVE = "rides/active";
    String RIDE_START = "rides/start";
    String RIDE_END = "rides/end";

    String EMAIL = "email";
    String VEHICLE_ID = "vehicleId";

    /**
     * Returns the list of rides by the email address.
     *
     * @param email
     * @return
     */
    public List<Ride> getRidesByEmail(String email);

    /**
     * Returns the list of rides by status.
     *
     * @param status
     * @return
     */
    public List<Ride> getByStatus(String status);

    /**
     * @param payload
     * @return
     */
    public Ride startRide(Payload payload);

    /**
     * @param payload
     * @return
     */
    public Ride endRide(Payload payload);

}
