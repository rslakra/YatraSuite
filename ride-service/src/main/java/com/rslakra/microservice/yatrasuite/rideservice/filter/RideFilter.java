package com.rslakra.microservice.yatrasuite.rideservice.filter;

import com.devamatre.appsuite.core.Payload;
import com.devamatre.appsuite.spring.filter.AbstractFilterImpl;

import java.util.Map;

/**
 * @author Rohtash Lakra
 * @created 2/8/23 1:06 PM
 */
public final class RideFilter extends AbstractFilterImpl {

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

    /**
     * @param allParams
     */
    public RideFilter(Map<String, Object> allParams) {
        super(allParams);
    }
}
