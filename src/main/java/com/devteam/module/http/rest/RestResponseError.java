package com.devteam.module.http.rest;

import com.devteam.lib.util.error.ErrorType;
import com.devteam.lib.util.error.RuntimeError;
import com.devteam.lib.util.jvm.JVMThread;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.InvocationTargetException;

@Getter
@Setter
public class RestResponseError {

    private String    errorSource;
    private ErrorType errorType;
    private String    message;
    private String    stacktrace;

    public RestResponseError() {}

    public RestResponseError(String errSource, Throwable error) {
        if(error instanceof InvocationTargetException) {
            init(errSource, ((InvocationTargetException) error).getCause());
        } else {
            init(errSource, error);
        }
    }

    void init(String errSource, Throwable error) {
        errorSource = errSource;
        message = error.getMessage();

        if(error instanceof javax.validation.ConstraintViolationException ||
                error instanceof org.hibernate.exception.ConstraintViolationException ||
                error instanceof org.springframework.dao.DataIntegrityViolationException) {
            errorType = ErrorType.ConstraintViolation;
        } else if(error instanceof IllegalStateException) {
            errorType = ErrorType.IllegalState;
        } else if(error instanceof IllegalArgumentException) {
            errorType = ErrorType.IllegalArgument;
        } else if(error instanceof RuntimeError) {
            RuntimeError sError = (RuntimeError) error;
            errorType = sError.getErrorType();
        } else {
            errorType = ErrorType.Unknown;
        }
        stacktrace = JVMThread.getPrintStackTrace(error.getStackTrace());
    }
}
