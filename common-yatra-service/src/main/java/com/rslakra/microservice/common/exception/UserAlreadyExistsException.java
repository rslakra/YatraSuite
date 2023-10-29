package com.rslakra.microservice.common.exception;

/**
 * Thrown when the user we're trying to add already exists (duplicate email address)
 *
 * @author Rohtash Lakra
 * @created 7/27/23 8:39 PM
 */
public class UserAlreadyExistsException extends ClientException {

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
