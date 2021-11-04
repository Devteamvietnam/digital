package com.devteam.module.data.db;

import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.devteam.module.common.ClientInfo;

abstract public class AbstractDBModuleScenario {

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public void initializeWithTransaction(ClientInfo client, ApplicationContext context, DBScenario scenario) throws Exception {
    JPAService service = context.getBean(JPAService.class);
    initialize(client, scenario);
    service.getEntityManager().flush();
  }

  abstract public void initialize(ClientInfo client, DBScenario scenario) throws Exception ;
}
