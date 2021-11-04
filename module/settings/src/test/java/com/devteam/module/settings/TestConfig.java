package com.devteam.module.settings;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.devteam.module.common.ClientInfo;
import com.devteam.module.data.db.DBService;
import com.devteam.module.data.db.JpaConfiguration;
import com.devteam.module.data.db.sample.EntityDB;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
  webEnvironment=WebEnvironment.NONE,
  properties = {
    "spring.config.location=classpath:application-test.yaml",
    "spring.main.allow-bean-definition-overriding=true",
  },
  classes = { JpaConfiguration.class, ModuleSettingsConfig.class }
)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
abstract public class TestConfig {
  protected ClientInfo clientInfo = ClientInfo.DEFAULT;

  @Autowired
  protected ApplicationContext context;

  @Autowired
  protected DBService dbService;

  @BeforeEach
  public void clearDataDB() throws Exception {
    EntityDB.initDataDB(context);
  }
}
