package com.devteam.module.security.plugin;

import java.util.List;

import com.devteam.module.security.entity.App;
import org.springframework.context.ApplicationContext;

import com.devteam.module.common.ClientInfo;
import com.devteam.module.security.entity.AppPermission;
import com.devteam.module.data.db.plugin.ServicePlugin;

abstract public class InitSecurityDBPlugin extends ServicePlugin {

  protected InitSecurityDBPlugin(String type) {
    super("security", "SecurityService", type);
  }

  abstract public App[] getAvailableApps(ClientInfo client, ApplicationContext context) throws Exception ;

  abstract public <T> List<AppPermission> getInitAppPermissions(ClientInfo client, T vionCtx,  ApplicationContext context) throws Exception ;

  public <T> List<AppPermission> getSampleAppPermissions(ClientInfo client, T vionCtx,  ApplicationContext context) throws Exception {
    return null;
  }
}
