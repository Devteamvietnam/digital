package com.devteam.module.account.data.db.sample;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import com.devteam.module.account.service.AccountService;
import com.devteam.module.account.entity.AccountGroup;
import com.devteam.module.common.ClientInfo;
import com.devteam.module.data.db.sample.PersistableEntityAssert;
import com.devteam.module.data.db.sample.SampleData;
import com.devteam.util.dataformat.DataSerializer;

public class GroupData extends SampleData {
  @Autowired
  private AccountService service;

  public AccountGroup ACCOUNT_GROUP;

  public AccountGroup ACCOUNTS;
  public AccountGroup ACCOUNTS_ADMIN;

  public void initialize(ClientInfo client)  {
    ACCOUNT_GROUP       =
        new AccountGroup(null, "devteam", "devteam", "Dev Team");
    ACCOUNT_GROUP = service.createAccountGroup(client, null, ACCOUNT_GROUP);
    ACCOUNT_GROUP = service.getAccountGroup(client, ACCOUNT_GROUP.getPath());

    ACCOUNTS =
        new AccountGroup(ACCOUNT_GROUP,  "devteam", "staff", "staff group");

    ACCOUNTS_ADMIN =
        new AccountGroup(ACCOUNTS, "admin", "Admin", "Admin");


    AccountGroup[] GROUPS = new AccountGroup[] { ACCOUNTS };
    AccountGroup[] ACCOUNTS_CHILD = new AccountGroup[] { ACCOUNTS, ACCOUNTS_ADMIN };
    for(AccountGroup sel: GROUPS) {
      service.createAccountGroup(client, ACCOUNT_GROUP.getId(), sel);
    }

    ACCOUNTS = service.getAccountGroup(client, ACCOUNTS.getPath());
    for(AccountGroup sel: ACCOUNTS_CHILD) {
      service.createAccountGroup(client, ACCOUNTS.getId(), sel);
    }
  }

  public void assertAll(ClientInfo clientInfo) throws Exception {
    AccountGroup modifiedLabel = DataSerializer.JSON.clone(ACCOUNTS);
    modifiedLabel.setLabel("ACCOUNTS Update");

    AccountGroup modifiedDesc = DataSerializer.JSON.clone(ACCOUNTS);
    modifiedDesc.setDescription("Update Description");

    new AccountGroupAssert(clientInfo, ACCOUNTS)
    .assertCreateChild()
    .assertEntityCreated()
    .assertEntityUpdate()
    .assertSave(modifiedLabel, (updatedGroup) -> {
      Assertions.assertEquals("ACCOUNTS Update", updatedGroup.getLabel());
    }).assertSave(modifiedDesc, (updatedGroup) -> {
      Assertions.assertEquals("Update Description", updatedGroup.getDescription());
    });
  }

  public class AccountGroupAssert extends PersistableEntityAssert<AccountGroup> {

    public AccountGroupAssert(ClientInfo clientInfo, AccountGroup group) {
      super(clientInfo, group);

      this.methods = new EntityServiceMethods() {
        public AccountGroup load() {
          return service.getAccountGroup(client, group.getPath());
        }

        public AccountGroup save(AccountGroup clone) {
          service.updateAccountGroup(client, clone);
          return load();
        }
      };
    }

    public AccountGroupAssert assertCreateChild() {
      List<AccountGroup> childAccountGroup =
          service.findAccountGroupChildren(client, entity.getParentPath());
      assertNotNull(childAccountGroup.get(0));
      for (AccountGroup childGroup : childAccountGroup) {
        if (childGroup.getName().equals(entity.getName())) {
          assertEquals(childGroup.getParentPath(), entity.getParentPath());
          assertEquals(childGroup.getLabel(), entity.getLabel());
          assertEquals(childGroup.getDescription(), entity.getDescription());
          break;
        }
        Assertions.fail();
      }
      return this;
    }
  }
}
