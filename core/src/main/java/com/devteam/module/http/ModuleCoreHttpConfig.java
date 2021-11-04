package com.devteam.module.http;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.devteam.module.app.ModuleSpringFrameworkConfig;

@Configuration
@ComponentScan(basePackages = {
        "com.devteam.module.http"
   }
)
@Import(value = {
    ModuleSpringFrameworkConfig.class
})
@EnableConfigurationProperties
public class ModuleCoreHttpConfig {
}