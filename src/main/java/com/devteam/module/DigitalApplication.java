package com.devteam.module;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@SpringBootApplication(
        exclude = {
                SecurityAutoConfiguration.class
        }
)
public class DigitalApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigitalApplication.class, args);
    }

}
