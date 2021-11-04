package com.devteam.module.data.db.util;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DBUtil {
  static public DataSource createHsqlDbDs(String username, String password, String url) {
    return createDs(org.hsqldb.jdbcDriver.class.getName(), username, password, url);
  }

  static DataSource createDs(String driver, String username, String password, String url) {
    DataSourceBuilder<?> builder =
        DataSourceBuilder.create().
        type(DriverManagerDataSource.class).
        username(username).password(password).url(url).
        driverClassName(driver);
    return builder.build();
  }
}
