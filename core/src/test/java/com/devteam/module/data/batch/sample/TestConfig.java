package com.devteam.module.data.batch.sample;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.devteam.module.common.ClientInfo;


@ExtendWith(SpringExtension.class)
@SpringBootTest(
  properties = {
    "spring.config.location=src/test/resources/application-test.yaml"
  },
  classes = {
    MyTaskBatchConfig.class
  }
)
@EnableAutoConfiguration(
  exclude = { DataSourceAutoConfiguration.class, SecurityAutoConfiguration.class }
)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
abstract public class TestConfig {

  protected ClientInfo clientInfo = ClientInfo.DEFAULT;

  @Autowired
  ApplicationContext context;

}
