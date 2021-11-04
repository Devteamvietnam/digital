package com.devteam.tutorial.algorithms.jdbc;

import java.sql.SQLException;

import javax.sql.XAConnection;

public interface DbService {
  public XAConnection getConnection() throws SQLException ;

  public void destroy() throws SQLException ;
}
