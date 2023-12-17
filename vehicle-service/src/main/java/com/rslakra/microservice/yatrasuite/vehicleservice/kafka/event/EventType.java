package com.rslakra.microservice.yatrasuite.vehicleservice.kafka.event;

import java.util.Arrays;

/**
 * @author Rohtash Lakra
 * @created 7/31/23 1:42 PM
 */
public enum EventType {

    RideStarted,
    RideEnded;

    /**
     * @param eventType
     * @return
     */
    public static EventType ofString(String eventType) {
        return Arrays.stream(values())
            .filter(entry -> entry.name().equalsIgnoreCase(eventType))
            .findFirst()
            .orElse(null);
    }

}
