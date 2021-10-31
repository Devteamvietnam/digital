package com.devteam.core.data.db.util;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

public class DBUtil {

  static DataSource createDs(String driver, String username, String password, String url) {
    DataSourceBuilder<?> builder =
        DataSourceBuilder.create().
        type(DriverManagerDataSource.class).
        username(username).password(password).url(url).
        driverClassName(driver);
    return builder.build();
  }
}
