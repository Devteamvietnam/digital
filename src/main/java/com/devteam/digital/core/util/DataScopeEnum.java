package com.devteam.digital.core.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * Data permission enumeration
 * </p>
 */
@Getter
@AllArgsConstructor
public enum DataScopeEnum {

    /* All data permissions */
    ALL("All", "Full data permissions"),

    /* Data authority of own department */
    THIS_LEVEL("This level", "Data authority of own department"),

    /* Data authority of own department */
    CUSTOMIZE("Customize", "Custom data permissions");

    private final String value;
    private final String description;

    public static DataScopeEnum find(String val) {
        for (DataScopeEnum dataScopeEnum : DataScopeEnum.values()) {
            if (val.equals(dataScopeEnum.getValue())) {
                return dataScopeEnum;
            }
        }
        return null;
    }

}
