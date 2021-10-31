package com.devteam.digital.security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ConfigBeanConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "login")
    public LoginProperties loginProperties() {
        return new LoginProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "jwt")
    public SecurityProperties securityProperties() {
        return new SecurityProperties();
    }
}

