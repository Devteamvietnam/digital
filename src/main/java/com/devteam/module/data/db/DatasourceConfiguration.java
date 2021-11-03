package com.devteam.module.data.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@EnableConfigurationProperties
@EnableAutoConfiguration
public class DatasourceConfiguration extends HikariConfig {
  @Primary
  @Bean("datasource")
  @ConfigurationProperties("spring.datasource")
  public DataSource dataSource() throws SQLException {
    System.out.println("\n\nDevteam CREATE DATASOURCE\n\n");
    return DataSourceBuilder.create().type(HikariDataSource.class).build();
  }
}