package com.devteam.tutorial.jdbc;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.devteam.tutorial.algorithms.jdbc.DbService;
import com.devteam.tutorial.algorithms.jdbc.HSQLDbService;
import com.devteam.tutorial.algorithms.jdbc.Teacher;
import com.devteam.tutorial.algorithms.jdbc.TeacherDAO;

public class TeacherDAOUnitTest {

  private DbService  dbService;
  private TeacherDAO dao;

  @BeforeEach
  public void before() throws Exception {
    dbService = new HSQLDbService("test");
    dao = new TeacherDAO(dbService);
  }

  @Test
  @Tag("unit")
  public void testCrud() throws Exception {
    // test create
    dao.createTable();
    assertEquals("Expect 0 records in the teacher table", 0, dao.count());
    // test insert
    Teacher teacher = new Teacher("John", "lee", 20);
    dao.insert(teacher);
    System.out.println("Insert: " + teacher);
    assertEquals("Expect 1 records in the teacher table", 1, dao.count());
    // test retrieve
    teacher = dao.retrieve(1);
    dao.retrieveByFirstName("John");
    System.out.println("Retrieve: " + teacher);
  }
}
