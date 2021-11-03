package com.devteam.module.server.plugin;

import com.devteam.module.account.entity.Account;
import com.devteam.module.account.plugin.AccountServicePlugin;
import com.devteam.module.common.ClientInfo;
import com.devteam.module.enums.Capability;
import com.devteam.module.enums.StorageState;
import com.devteam.module.security.SecurityService;
import com.devteam.module.security.entity.App;
import com.devteam.module.security.entity.AppPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountInitSecurityPlugin extends AccountServicePlugin {

  @Autowired
  SecurityService service;

  protected AccountInitSecurityPlugin() {
    super("account-security");
  }

  public void onPreSave(ClientInfo client, Account account, boolean isNew) {
  }

  public void onPostSave(ClientInfo client, Account account, boolean isNew) {
    List<AppPermission> permissions = new ArrayList<>();
    App mySpaceApp = service.getApp(client, "user", "user");
    permissions.add(new AppPermission( account.getLoginId()).withCapability(Capability.Write)
        .withApp(mySpaceApp));
    service.saveAppPermissions(client, permissions);
  }

  public void onPostStateChange(ClientInfo client, Account account, StorageState newState) {
    if (newState.equals(StorageState.ARCHIVED)) {
      List<AppPermission> accountPermissions = service.findAppPermissions(client,
              account.getLoginId());
      service.deletePermissions(client, accountPermissions);
    }
  }
}
