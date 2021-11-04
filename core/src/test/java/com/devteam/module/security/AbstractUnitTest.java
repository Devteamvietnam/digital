package com.devteam.module.security;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.devteam.module.data.db.JpaConfiguration;
import com.devteam.module.data.db.sample.EntityDB;

@RunWith(SpringRunner.class)
@ContextConfiguration(
  classes = {
    JpaConfiguration.class, ModuleCoreSecurityConfig.class
})
@TestPropertySource(
  locations = { "/application-test.properties" }
)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
abstract public class AbstractUnitTest {
  @Autowired
  ApplicationContext context;

  @Before
  public void clearDataDB() throws Exception {
    EntityDB.initDataDB(context);
  }
}
