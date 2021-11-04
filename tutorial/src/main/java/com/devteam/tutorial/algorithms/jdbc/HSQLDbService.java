package com.devteam.tutorial.algorithms.jdbc;

import java.sql.SQLException;

import javax.sql.XAConnection;

import org.hsqldb.jdbc.pool.JDBCXADataSource;
import org.hsqldb.DatabaseManager;

public class HSQLDbService implements DbService {
  private JDBCXADataSource dataSource;
  
  public HSQLDbService(String dbName) throws Exception {
    dataSource = new JDBCXADataSource();
    dataSource.setDatabase("jdbc:hsqldb:mem:" + dbName);
  }
  
  public XAConnection getConnection() throws SQLException {
    return dataSource.getXAConnection();
  }
  
  public void destroy() throws SQLException {
    DatabaseManager.closeDatabases(1);
  }
}
