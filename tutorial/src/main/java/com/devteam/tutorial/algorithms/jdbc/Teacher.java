package com.devteam.tutorial.algorithms.jdbc;

public class Teacher {
  private long id;
  private String firstName;
  private String lastName;
  private int age;
  
  public Teacher() {}
  
  public Teacher(String fName, String lName, int age) {
    this.firstName = fName;
    this.lastName  = lName;
    this.age       = age;
  }
  
  public long getId() { return id; }
  public void setId(long id) { this.id = id; }
  
  public String getName() { return firstName; }
  public void setName(String name) { this.firstName = name; }
  
  public String getLastName() { return lastName; }
  public void setLastName(String lastName) { this.lastName = lastName; }

  public int getAge() { return age; }
  public void setAge(int age) { this.age = age; }
}
