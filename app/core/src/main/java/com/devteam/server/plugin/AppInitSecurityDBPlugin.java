package com.devteam.server.plugin;

import java.util.ArrayList;
import java.util.List;

import com.devteam.module.account.service.AccountService;
import com.devteam.module.account.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.devteam.module.common.ClientInfo;
import com.devteam.module.security.SecurityService;
import com.devteam.module.security.entity.App;
import com.devteam.module.security.entity.AppPermission;
import com.devteam.module.enums.Capability;
import com.devteam.module.security.plugin.InitSecurityDBPlugin;

@Component
public class AppInitSecurityDBPlugin extends InitSecurityDBPlugin {

  @Autowired
  SecurityService service;

  @Autowired
  AccountService accountService;

  public AppInitSecurityDBPlugin() {
    super("App");
  }

  public App[] getAvailableApps(ClientInfo client, ApplicationContext context) throws Exception {
    return createApps();
  }

  public <T> List<AppPermission> getInitAppPermissions(ClientInfo client, T vionCtx, ApplicationContext context)
      throws Exception {
    Account account = (Account) vionCtx;
    String adminUser = account.getAdminAccountLoginId();
    if (adminUser == null)
      adminUser = "admin";
    App[] apps = createApps();
    List<AppPermission> permissions = new ArrayList<>();

    for (App selApp : apps) {
      App app = service.getApp(client, selApp.getModule(), selApp.getName());
      AppPermission permission = new AppPermission(adminUser).withCapability(Capability.Admin)
          .withApp(app);
      if (!adminUser.equals("admin")) {
        permission.withCapability(Capability.Moderator);
      }
      permissions.add(permission);
    }
    return permissions;
  }

  public <T> List<AppPermission> getSampleAppPermissions(ClientInfo client, ApplicationContext context)
      throws Exception {
    Account thien = accountService.getAccountById(client, "thien");
    Account thiendinh = accountService.getAccountById(client, "thiendinh");
    Account ddthien = accountService.getAccountById(client, "devteam");

    if (thien != null) {
      List<AppPermission> thienExistPermissions = service.findAppPermissions(client, thien.getLoginId());
      service.deletePermissions(client, thienExistPermissions);
    }

    if (ddthien != null) {
      List<AppPermission> ddthienExistPermissions = service.findAppPermissions(client, ddthien.getLoginId());
      service.deletePermissions(client, ddthienExistPermissions);
    }

    if (thiendinh != null) {
      List<AppPermission> thiendinhExistPermissions = service.findAppPermissions(client, thiendinh.getLoginId());
      service.deletePermissions(client, thiendinhExistPermissions);
    }

    List<AppPermission> permissions = new ArrayList<>();
    App[] apps = createApps();
    for (App selApp : apps) {
      App app = service.getApp(client, selApp.getModule(), selApp.getName());
      if (thien != null) {
        AppPermission permission = new AppPermission(thien.getLoginId())
            .withCapability(Capability.Moderator).withApp(app);
        permissions.add(permission);
      }
      if (thiendinh != null) {
        AppPermission permission = new AppPermission(thiendinh.getLoginId())
            .withCapability(Capability.Write).withApp(app);
        permissions.add(permission);
      }
      if (ddthien != null) {
        AppPermission permission = new AppPermission(ddthien.getLoginId())
            .withCapability(Capability.Read).withApp(app);
        permissions.add(permission);
      }
    }
    return permissions;
  }

  private App[] createApps() {
    App[] apps = { new App("user", "user").withRequiredCapability(Capability.None),
        new App("admin", "admin").withRequiredCapability(Capability.Read),
        new App("system", "system").withRequiredCapability(Capability.Read) };
    return apps;
  }
}
