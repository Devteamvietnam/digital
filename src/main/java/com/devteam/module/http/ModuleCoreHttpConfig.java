package com.devteam.module.http;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "com.devteam.module.http"
   }
)
@EnableConfigurationProperties
public class ModuleCoreHttpConfig {
}