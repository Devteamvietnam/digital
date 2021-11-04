package com.devteam.module.security;

import com.devteam.module.security.entity.App;
import com.devteam.module.security.entity.AppPermission;
import com.devteam.module.enums.Capability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.devteam.module.common.ClientInfo;
import com.devteam.module.data.db.DBServicePlugin;

@Component
@Order(value = DBServicePlugin.CORE_SECURITY)
public class TestSecurityDBServicePlugin extends DBServicePlugin {

  @Autowired
  SecurityService service;

  public void initDb(ClientInfo client, ApplicationContext context) throws Exception {
    System.out.println("Init TestSecurityDBServicePlugin............................");
    App APP1 = new App("module1", "app1").withRequiredCapability(Capability.Admin);
    App APP2 = new App("module2", "app2").withRequiredCapability(Capability.Admin);
    App[] ALL_APP = { APP1, APP2 };
    String[] loginIds = { "admin", "user" };

    for (App app : ALL_APP) {
      app = service.saveApp(client, app);
        for (String loginId : loginIds) {
          service.saveAppPermisson(client,
              new AppPermission(loginId).withApp(app).withCapability(Capability.Admin));
      }
    }
  }
}
