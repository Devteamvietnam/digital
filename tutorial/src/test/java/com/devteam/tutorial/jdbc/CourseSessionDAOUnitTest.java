package com.devteam.tutorial.jdbc;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.devteam.tutorial.algorithms.jdbc.DbService;
import com.devteam.tutorial.algorithms.jdbc.HSQLDbService;
import com.devteam.tutorial.algorithms.jdbc.Student;
import com.devteam.tutorial.algorithms.jdbc.StudentDAO;

public class CourseSessionDAOUnitTest {
  private DbService  dbService;
  private StudentDAO dao;

  @BeforeEach
  public void before() throws Exception {
    dbService = new HSQLDbService("test");
    dao       = new StudentDAO(dbService);
  }
  
  @Test
  public void testCrud() throws Exception {
    dao.createTable();
    Assert.assertEquals("Exepect 0 records in the student table", 0, dao.count());
  
    Student studentThien = new Student("Thien", "Dinh", 20);
    System.out.println("INSERT: ");
    System.out.println(studentThien);
    dao.insert(studentThien);
    Assert.assertEquals("Exepect 1 records in the student table", 1, dao.count());
  
    dao.insert(new Student("Thien", "Dinh", 20));
    
    Assert.assertEquals("Exepect 2 records in the student table", 2, dao.count());
    studentThien = dao.retrieve(1);
    dao.retrieveByFirstName("Thien");
    System.out.println("RETRIEVE: ");
    System.out.println(studentThien);
  }
}

