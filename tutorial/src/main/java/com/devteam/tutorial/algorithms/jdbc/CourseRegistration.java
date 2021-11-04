package com.devteam.tutorial.algorithms.jdbc;

public class CourseRegistration {
  private long studentID;
  private long sessionID;
  private long score;
  
  public CourseRegistration() {}
  
  public CourseRegistration(long studentID, long sessionID, long score) {
    this.studentID = studentID;
    this.sessionID = sessionID;
    this.score     = score;
  }
  
  public long getStudentID() { return studentID; }
  public void setStudentID(long studentID) { this.studentID = studentID; }
  
  public long getSessionID() { return sessionID; }
  public void setSessionID(long sessionID) { this.sessionID = sessionID; }
  
  public long getScore() { return score; }
  public void setScore(long score) { this.score = score; }
}
