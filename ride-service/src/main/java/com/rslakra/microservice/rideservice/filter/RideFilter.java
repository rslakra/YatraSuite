package com.rslakra.microservice.rideservice.filter;

import com.rslakra.frameworks.core.Payload;
import com.rslakra.frameworks.spring.filter.AbstractFilterImpl;

/**
 * @author Rohtash Lakra
 * @created 2/8/23 1:06 PM
 */
public final class RideFilter extends AbstractFilterImpl<Object> {

    public static final String USER_EMAIL = "userEmail";
    public static final String VEHICLE_ID = "vehicleId";
    public static final String START_TIME = "startTime";
    public static final String END_TIME = "endTime";

    /**
     * @param allParams
     */
    public RideFilter(Payload<String, Object> allParams) {
        super(allParams);
    }
}