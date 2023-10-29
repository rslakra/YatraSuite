package com.rslakra.microservice.vehicleservice.kafka.event;

/**
 * @author Rohtash Lakra
 * @created 7/31/23 1:36 PM
 */
public interface Event {

    /**
     * @return
     */
    public EventType getType();

}
