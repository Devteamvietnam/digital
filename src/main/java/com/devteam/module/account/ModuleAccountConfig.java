package com.devteam.module.account;

import com.devteam.core.data.db.JpaConfiguration;
import com.devteam.core.http.ModuleCoreHttpConfig;
import com.devteam.module.security.ModuleCoreSecurityConfig;
import com.devteam.module.storage.ModuleStorageConfig;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@ComponentScan(
   basePackages = {"com.devteam.module.account"}
)
@EnableJpaRepositories(
  basePackages = {"com.devteam.module.account.repository"}
)
@EnableConfigurationProperties
@EnableTransactionManagement
@Import(value = {
  JpaConfiguration.class, ModuleCoreHttpConfig.class,
  ModuleStorageConfig.class, ModuleCoreSecurityConfig.class
})
public class ModuleAccountConfig {

  @Bean
  public PasswordEncoder encoder() { return new BCryptPasswordEncoder(); }
}