package com.devteam.server.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.devteam.module.account.service.AccountService;
import com.devteam.module.account.entity.Account;
import com.devteam.module.common.ClientInfo;
import com.devteam.module.data.db.DBService;
import com.devteam.module.data.db.sample.EntityDB;
import com.devteam.util.text.DateUtil;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataInitService {
  @Getter
  @Autowired
  private ApplicationContext context;

  @Autowired
  private DBService dbService ;

  @Autowired
  private AccountService accountService;

  @Value("${app.data.init-sample:false}")
  private boolean initSample;

  @Value("${app.data.init-demo:false}")
  private boolean initDemo;

  @Order(1)
  @EventListener({ ContextRefreshedEvent.class })
  void onContextRefreshedEvent() {
    runInitData(ClientInfo.DEFAULT);
  }


  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public void runInitData(ClientInfo client) {
    log.info("Start Data Import");
    long start = System.currentTimeMillis();
    try {
      Account admin = accountService.getAccount(client, "admin");
      if(admin == null) {
        EntityDB.initDataDB(context);
        dbService.initDb(client, initSample);
        EntityDB.clearDataDB();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    long execTime = System.currentTimeMillis() - start;
    System.out.println("Import in " + (DateUtil.asHumanReadable(execTime)));
  }
}
