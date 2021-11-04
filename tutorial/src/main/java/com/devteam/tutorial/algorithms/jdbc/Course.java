package com.devteam.tutorial.algorithms.jdbc;

public class Course {
  private long id;
  private String name;
  private long fee;
  
  public Course() {}
  
  public Course(String name, long fee) {
    this.name = name;
    this.fee  = fee;
  }
  
  public long getId() { return id; }
  public void setId(long id) { this.id = id; }
  
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  
  public long getFee() { return fee; }
  public void setFee(long fee) { this.fee = fee; }
}
