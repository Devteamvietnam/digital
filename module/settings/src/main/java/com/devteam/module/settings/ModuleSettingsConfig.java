package com.devteam.module.settings;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@ComponentScan(
    basePackages = {
            "com.devteam.module.settings",
    }
)
@EnableJpaRepositories(
  basePackages = {
          "com.devteam.module.settings.location.repository",
          "com.devteam.module.settings.currency.repository",
          "com.devteam.module.settings.resource.repository"
  }
)
@EnableConfigurationProperties
@EnableTransactionManagement
public class ModuleSettingsConfig {
}