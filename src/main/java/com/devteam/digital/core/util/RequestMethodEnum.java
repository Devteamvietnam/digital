package com.devteam.digital.core.util;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum RequestMethodEnum {

    /**
     * search @AnonymousGetMapping
     */
    GET("GET"),

    /**
     * search @AnonymousPostMapping
     */
    POST("POST"),

    /**
     * search @AnonymousPutMapping
     */
    PUT("PUT"),

    /**
     * search @AnonymousPatchMapping
     */
    PATCH("PATCH"),

    /**
     *search @AnonymousDeleteMapping
     */
    DELETE("DELETE"),

    /**
     * Otherwise it's all Request All interfaces are released
     */
    ALL("All");

    /**
     * Request Types of
     */
    private final String type;

    public static RequestMethodEnum find(String type) {
        for (RequestMethodEnum value : RequestMethodEnum.values()) {
            if (type.equals(value.getType())) {
                return value;
            }
        }
        return ALL;
    }
}

