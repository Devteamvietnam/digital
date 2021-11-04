package com.devteam.module.account;

import com.devteam.module.account.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.devteam.module.common.ServiceMethodCallback;
import com.devteam.module.data.db.DBService;
import com.devteam.module.data.db.sample.EntityDB;
import com.devteam.module.settings.ModuleSettingsConfig;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
    webEnvironment=WebEnvironment.NONE,
    classes = { ModuleSettingsConfig.class, ModuleAccountConfig.class },
    properties = {
      "spring.config.location=classpath:application-test.yaml"
    }
)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
abstract public class TestConfig {
  static public class DummyAccountServiceCallback implements ServiceMethodCallback<AccountService> {
    AccountService service;

    public DummyAccountServiceCallback(AccountService service) {
      this.service = service;
    }

    public void onPreMethod() {
      System.out.println("pre method call");
    }

    public void onPostMethod() {
      throw new RuntimeException("Fail. Expect a rollback if method annotate with the Transactionnal");
    }
  }

  @Autowired
  ApplicationContext context;

  @Autowired
  protected DBService dbService ;

  @BeforeEach
  public void clearDataDB() throws Exception {
    EntityDB.initDataDB(context);
  }
}
