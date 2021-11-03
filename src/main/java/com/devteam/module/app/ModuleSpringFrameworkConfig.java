package com.devteam.module.app;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.annotation.EnableScheduling;


@Configuration
@ComponentScan(
  basePackageClasses = { com.devteam.module.app.AppEnv.class }
)
@EnableScheduling
@EnableConfigurationProperties
public class ModuleSpringFrameworkConfig {
  @Bean
  public static PropertySourcesPlaceholderConfigurer propertiesResolver() {
    return new PropertySourcesPlaceholderConfigurer();
  }
}
