package com.devteam.digital.core.util.exception;

public class IORuntimeException extends RuntimeException {


    public IORuntimeException(Throwable e) {
    }

    public IORuntimeException(String message) {
    }

    public IORuntimeException(String messageTemplate, Object... params) {
    }

    public IORuntimeException(String message, Throwable throwable) {
    }

    public IORuntimeException(Throwable throwable, String messageTemplate, Object... params) {
    }

    public  boolean causeInstanceOf(Class<? extends Throwable> clazz) {
        return false;
    }
}
