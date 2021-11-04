package com.devteam.tutorial.jdbc;

import java.sql.SQLException;

import com.devteam.tutorial.algorithms.jdbc.Student;
import com.devteam.tutorial.algorithms.jdbc.StudentDAO;

public class DataInitializer {
  public Student   STUDENT_THIEN = new Student("Thien", "Dinh", 20);
  public Student[] STUDENTS    = { STUDENT_THIEN };
  
  private StudentDAO studentDAO;

  //TODO: implement TeacherDAO
  //private TeacherDAO teacherDAO;

  //TODO: implement CourseDAO
  //private CourseDAO courseDAO;

  //TODO: implement CourseSessionDAO

  //TODO: implement CourseRegistrationDAO
  
  
  public DataInitializer(StudentDAO studentDAO) {
    this.studentDAO = studentDAO;
  }
  
  public void initTable() throws SQLException {
    studentDAO.createTable();
  }
  
  public void initData() throws SQLException {
    for(Student sel : STUDENTS) {
      studentDAO.insert(sel);
    }
    
    
    //TODO: init teacher data

    //TODO: init course data

    //TODO: init course session data

    //TODO: init course registration data
  }

}
