package com.devteam.digital.security.config;

import lombok.Data;

/**
 * Jwt Parameter configuration
 *
 */
@Data
public class SecurityProperties {

    /**
     * Request Headers ： Authorization
     */
    private String header;

    /**
     * Token prefix, leave a space at the end Bearer
     */
    private String tokenStartWith;

    /**
     * The token must be encoded with a Base64 of at least 88 bits
     */
    private String base64Secret;

    /**
     * Token expiration time here unit/ms
     */
    private Long tokenValidityInSeconds;

    /**
     * Online user key, query the data of online users in redis according to the key
     */
    private String onlineKey;

    /**
     * Codekey
     */
    private String codeKey;

    /**
     * token
     */
    private Long detect;

    /**
     * Renewal time
     */
    private Long renew;

    public String getTokenStartWith() {
        return tokenStartWith + " ";
    }
}

