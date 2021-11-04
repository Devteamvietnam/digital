package com.devteam.server;

import com.devteam.module.filter.FilterConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.devteam.module.account.ModuleAccountConfig;
import com.devteam.module.security.ModuleCoreSecurityConfig;

@Configuration
@ComponentScan(
    basePackages = {
            "com.devteam.server",
            "com.devteam.module.http"
    }
)
@EnableConfigurationProperties
@SpringBootApplication(
  exclude = {
    SecurityAutoConfiguration.class
  }
)
@Import(value = {
    ModuleCoreSecurityConfig.class, ModuleAccountConfig.class,
    WebSecurityConfig.class, WebResourceConfig.class, FilterConfig.class
})
public class ServerAppConfig {
}