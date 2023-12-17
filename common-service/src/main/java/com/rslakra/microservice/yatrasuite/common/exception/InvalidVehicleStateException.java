package com.rslakra.microservice.yatrasuite.common.exception;

/**
 * Thrown when we attempt to do something a vehicle that's not in the correct status (e.g., start a ride on a vehicle
 * that's already marked as "in use"
 *
 * @author Rohtash Lakra
 * @created 7/27/23 8:39 PM
 */
public class InvalidVehicleStateException extends ClientException {

    /**
     *
     */
    public InvalidVehicleStateException() {
    }

    public InvalidVehicleStateException(String message) {
        super(message);
    }
}
