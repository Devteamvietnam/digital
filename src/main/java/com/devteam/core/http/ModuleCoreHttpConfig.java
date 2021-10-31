package com.devteam.core.http;

import com.devteam.springframework.ModuleSpringFrameworkConfig;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {
        "com.devteam.core.http"
   }
)
@Import(value = {
    ModuleSpringFrameworkConfig.class
})
@EnableConfigurationProperties
public class ModuleCoreHttpConfig {
}