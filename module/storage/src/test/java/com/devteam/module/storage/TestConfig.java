package com.devteam.module.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.devteam.module.data.db.JpaConfiguration;
import com.devteam.module.data.db.sample.EntityDB;
import com.devteam.module.http.ModuleCoreHttpConfig;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
    webEnvironment=WebEnvironment.NONE,
    classes = { JpaConfiguration.class, ModuleCoreHttpConfig.class, ModuleStorageConfig.class },
    properties = {
      "spring.config.location=classpath:application-test.yaml"
    }
)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
abstract public class TestConfig {

  @Autowired
  ApplicationContext context;

  @BeforeEach
  public void clearDataDB() throws Exception {
    EntityDB.initDataDB(context);
  }
}
