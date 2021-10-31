package com.devteam.digital.core.util.exception;

/**
 *Unify information about misconfiguration
 *
 */
public class BadConfigurationException extends RuntimeException {

    public BadConfigurationException() {
        super();
    }


    public BadConfigurationException(String message) {
        super(message);
    }

    public BadConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadConfigurationException(Throwable cause) {
        super(cause);
    }

    protected BadConfigurationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

