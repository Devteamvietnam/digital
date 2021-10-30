package com.devteam.digital.core.util.exception;


import com.devteam.digital.core.util.StringUtil;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Class clazz, String field, String val) {
        super(EntityNotFoundException.generateMessage(clazz.getSimpleName(), field, val));
    }

    private static String generateMessage(String entity, String field, String val) {
        return StringUtil.capitalize(entity)
                + " with " + field + " "+ val + " does not exist";
    }
}