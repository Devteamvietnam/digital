package com.devteam.module.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

@Profile("data")
@Configuration
@Import(value = {
})
public class DataProfileConfigConfig {
  @Bean("TestService")
  public DataInitService createTestDataService() {
    return new DataInitService();
  }
}
