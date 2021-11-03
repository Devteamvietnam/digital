package com.devteam.module.security.plugin;

import com.devteam.module.common.ClientInfo;
import com.devteam.module.data.db.plugin.ServicePlugin;
import com.devteam.module.security.entity.App;
import com.devteam.module.security.entity.AppPermission;
import org.springframework.context.ApplicationContext;

import java.util.List;

abstract public class InitSecurityDBPlugin extends ServicePlugin {

  protected InitSecurityDBPlugin(String type) {
    super("security", "SecurityService", type);
  }

  abstract public App[] getAvailableApps(ClientInfo client, ApplicationContext context) throws Exception ;

  abstract public <T> List<AppPermission> getInitAppPermissions(ClientInfo client, T devteamCtx,  ApplicationContext context) throws Exception ;

  public <T> List<AppPermission> getSampleAppPermissions(ClientInfo client, T devteamCtx,  ApplicationContext context) throws Exception {
    return null;
  }
}
