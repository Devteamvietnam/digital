package com.devteam.tutorial.jdbc;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.devteam.tutorial.algorithms.jdbc.DbService;
import com.devteam.tutorial.algorithms.jdbc.HSQLDbService;
import com.devteam.tutorial.algorithms.jdbc.Student;
import com.devteam.tutorial.algorithms.jdbc.StudentDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class MyJDBCUnitTest {
  private DbService  dbService;
  private StudentDAO studentDAO;

  private DataInitializer DATA ;

  @BeforeEach
  public void before() throws Exception {
    dbService   = new HSQLDbService("test");
    studentDAO  = new StudentDAO(dbService);
   
    DATA = new DataInitializer(studentDAO);
    DATA.initTable();
    DATA.initData();
  }
  
  @AfterEach
  public void teardown() throws Exception {
    dbService.destroy();
  }
  
  @Test
  public void testStudents() throws Exception {
    Assert.assertEquals("Exepect 2 records in the student table", DATA.STUDENTS.length, studentDAO.count());
    Student studentThien = studentDAO.retrieve(1);
    Assert.assertNotNull(studentThien);
    Assert.assertEquals(1, studentDAO.retrieveByFirstName("Thien").size());
  }

  @Test
  public void testTeachers() throws Exception {
    //TODO: implement this test
  }

  @Test
  public void testCourseRegistrations() throws Exception {
    //TODO: implement this test
  }
}

