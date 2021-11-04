package com.devteam.migration.db;

import com.devteam.module.filter.FilterConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Import;

import com.devteam.module.account.ModuleAccountConfig;
import com.devteam.module.security.ModuleCoreSecurityConfig;
import com.devteam.module.storage.ModuleStorageConfig;

@SpringBootApplication(
  exclude = {
    SecurityAutoConfiguration.class,
  }
)
@Import(value = {
    ModuleCoreSecurityConfig.class, ModuleAccountConfig.class, ModuleStorageConfig.class, FilterConfig.class
})
public class FlywayApp {
  public static void main(String[] args) {
    SpringApplication.run(FlywayApp.class, args);
  }
}