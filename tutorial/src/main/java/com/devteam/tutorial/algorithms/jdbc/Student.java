package com.devteam.tutorial.algorithms.jdbc;

public class Student {
  private long   id;
  private String firstName;
  private String lastName;
  private int    age;
  
  public Student() {}
  
  public Student(String fName, String lName, int age) {
    this.firstName = fName;
    this.lastName  = lName;
    this.age       = age;
  }
  
  public long getId() { return id; }
  public void setId(long id) { this.id = id; }
  
  public String getFirstName() { return firstName; }
  public void setFirstName(String firstName) { this.firstName = firstName; }
  
  public String getLastName() { return lastName; }
  public void setLastName(String lastName) { this.lastName = lastName; }
  
  public int getAge() { return age; }
  public void setAge(int age) { this.age = age; }
  
  public String toString() {
    StringBuilder b = new StringBuilder() ;
    b.append("firstName: ").append(firstName).append("\n");
    b.append("lastName:  ").append(lastName).append("\n");
    b.append("age:       ").append(age).append("\n");
    return b.toString();
  }
}
