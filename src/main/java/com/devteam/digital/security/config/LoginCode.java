package com.devteam.digital.security.config;

import lombok.Data;

/**
 * Login verification code configuration information
 *
 */
@Data
public class LoginCode {

    /**
     * Verification code configuration
     */
    private LoginCodeEnum codeType;
    /**
     Verification code validity period in minutes
     */
    private Long expiration = 2L;
    /**
     * Length of verification code
     */
    private int length = 2;
    /**
     * Verification code width
     */
    private int width = 111;
    /**
     * Verification code height
     */
    private int height = 36;
    /**
     * Verification code font
     */
    private String fontName;
    /**
     * font size
     */
    private int fontSize = 25;

    public LoginCodeEnum getCodeType() {
        return codeType;
    }
}

