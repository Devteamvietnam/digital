package com.devteam.module.security;

import com.devteam.module.common.ClientInfo;
import com.devteam.module.data.db.DBServicePlugin;
import com.devteam.module.security.entity.App;
import com.devteam.module.security.entity.AppPermission;
import com.devteam.module.security.plugin.InitSecurityDBPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Order(value = DBServicePlugin.CORE_SECURITY)
public class SecurityDBServicePlugin extends DBServicePlugin {
  @Autowired(required = false)
  List<InitSecurityDBPlugin> initDbPlugins = new ArrayList<>();

  @Autowired
  SecurityService service;

  public void initDb(ClientInfo client, ApplicationContext context) throws Exception {
    for (InitSecurityDBPlugin sel : initDbPlugins) {
      App[] apps = sel.getAvailableApps(client, context);
      for (App app : apps) {
        service.saveApp(client, app);
      }
    }
  }

  public <T> void createSammpleData(ClientInfo client, T devteamCtx, ApplicationContext context) throws Exception {
    for (InitSecurityDBPlugin sel : initDbPlugins) {
      saveAppPermissions(client, sel.getSampleAppPermissions(client, devteamCtx, context));
    }
  }

  void saveAppPermissions(ClientInfo client, List<AppPermission> permissions) throws Exception {
    if (permissions == null)
      return;
    for (AppPermission permision : permissions) {
      service.saveAppPermisson(client, permision);
    }
  }
}
