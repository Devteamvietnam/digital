package com.devteam;

import com.devteam.core.filter.FilterConfig;
import com.devteam.module.account.ModuleAccountConfig;
import com.devteam.module.security.ModuleCoreSecurityConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(
        basePackages = {
                "com.devteam.server",
                "com.devteam.core.http"
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
