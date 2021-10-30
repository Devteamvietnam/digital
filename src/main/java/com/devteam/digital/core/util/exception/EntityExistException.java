package com.devteam.digital.core.util.exception;

import com.devteam.digital.core.util.StringUtil;

public class EntityExistException extends RuntimeException {

    public EntityExistException(Class clazz, String field, String val) {
        super(EntityExistException.generateMessage(clazz.getSimpleName(), field, val));
    }

    private static String generateMessage(String entity, String field, String val) {
        return StringUtil.capitalize(entity)
                + " with " + field + " "+ val + " existed";
    }
}