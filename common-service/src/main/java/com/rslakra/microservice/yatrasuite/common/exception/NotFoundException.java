package com.rslakra.microservice.yatrasuite.common.exception;

/**
 * Thrown when we can't find the database entity being requested Thrown when the record already exists.
 *
 * @author Rohtash Lakra
 * @created 7/27/23 8:39 PM
 */
public class NotFoundException extends ClientException {

    /**
     * @param message
     */
    public NotFoundException(String message) {
        super(message);
    }
}
