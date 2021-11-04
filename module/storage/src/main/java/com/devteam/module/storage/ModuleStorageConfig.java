package com.devteam.module.storage;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.devteam.module.http.ModuleCoreHttpConfig;


@Configuration
@ComponentScan(basePackages = {"com.devteam.module.storage"})
@EnableJpaRepositories(
)
@EnableConfigurationProperties
@EnableTransactionManagement
@Import(ModuleCoreHttpConfig.class)
public class ModuleStorageConfig {
}