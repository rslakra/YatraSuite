package com.rslakra.microservice.yatrasuite.common.exception;

/**
 * Thrown the unchecked exception.
 *
 * @author Rohtash Lakra
 * @created 7/27/23 8:39 PM
 */
public class ClientException extends Exception {

    /**
     *
     */
    public ClientException() {
    }

    /**
     * @param message
     */
    public ClientException(String message) {
        super(message);
    }

    /**
     * @param pattern
     * @param args
     */
    public ClientException(final String pattern, final Object... args) {
        this(String.format("Record already exists with %s!", String.format(pattern, args)));
    }

    /**
     * @param message
     * @param cause
     */
    public ClientException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param pattern
     * @param cause
     * @param args
     */
    public ClientException(final String pattern, final Throwable cause, final Object... args) {
        this(String.format("Record already exists with %s!", String.format(pattern, args)), cause);
    }

    /**
     * @param cause
     */
    public ClientException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public ClientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
