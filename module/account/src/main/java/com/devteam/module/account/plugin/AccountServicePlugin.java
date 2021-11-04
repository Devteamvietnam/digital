package com.devteam.module.account.plugin;

import com.devteam.module.account.entity.Account;
import com.devteam.module.common.ClientInfo;
import com.devteam.module.enums.StorageState;
import com.devteam.module.data.db.plugin.ServicePlugin;

abstract public class AccountServicePlugin extends ServicePlugin {

  protected AccountServicePlugin(String type) {
    super("account", "AccountService", type);
  }

  public void onPreSave(ClientInfo client, Account account, boolean isNew) {
  }

  public void onPostSave(ClientInfo client, Account account, boolean isNew) {
  }

  public void onPreStateChange(ClientInfo client, Account account, StorageState newState) {
  }

  public void onPostStateChange(ClientInfo client, Account account, StorageState newState) {
  }
}
