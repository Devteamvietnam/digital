package com.devteam.tutorial.algorithms.jdbc;

public class CourseSession {
  private long id;
  private long courseID;
  private long time;
  private String teacher;
  
  public CourseSession() {}
  
  public CourseSession(long courseID, long time, String teacher ) {
    this.courseID = courseID;
    this.time     = time;
    this.teacher  = teacher;
  }
  
  public long getId() { return id; }
  public void setId(long id) { this.id = id;} 
  
  public long getCourseID() { return courseID; }
  public void setCourseID(long courseID) { this.courseID = courseID; }
  
  public long getTime() { return time; }
  public void setTime(long time) { this.time = time; }
  
  public String getTeacher() { return teacher; }
  public void setTeacher(String teacher) { this.teacher = teacher; }
  
}
