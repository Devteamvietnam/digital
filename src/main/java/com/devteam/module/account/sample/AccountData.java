package com.devteam.module.account.sample;

import com.devteam.common.ClientInfo;
import com.devteam.core.data.db.sample.PersistableEntityAssert;
import com.devteam.core.data.db.sample.SampleData;
import com.devteam.module.account.entity.Account;
import com.devteam.module.account.entity.OrgProfile;
import com.devteam.module.account.entity.UserIdentity;
import com.devteam.module.account.entity.UserProfile;
import com.devteam.module.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class AccountData extends SampleData {
  @Autowired
  protected AccountService accountService;

  public class AccountAssert extends PersistableEntityAssert<Account> {
    public AccountAssert(ClientInfo client, Account account) {
      super(client, account);
      this.methods = new EntityServiceMethods() {
        public Account load() {
          return accountService.getAccount(client, account.getLoginId());
        }

        public Account save(Account ac) {
          accountService.saveAccount(client, ac);
          return load();
        }

        public List<?> searchEntity() {
          return accountService.searchAccounts(client, createSearchQuery(entity.getLoginId()));
        }

        public boolean archive() {
          return accountService.changeAccountStorageState(client, createArchivedStorageRequest(entity));
        }
      };
    }
  }

  public class UserProfileAssert extends PersistableEntityAssert<UserProfile> {
    public UserProfileAssert(ClientInfo client, UserProfile profile) {
      super(client, profile);
      this.methods = new EntityServiceMethods() {

        public UserProfile save(UserProfile clone) {
          return accountService.saveUserProfile(client, clone);
        }
      };
    }
  }

  public class OrgProfileAssert extends PersistableEntityAssert<OrgProfile> {

    public OrgProfileAssert(ClientInfo client, OrgProfile profile) {
      super(client, profile);
      this.methods = new EntityServiceMethods() {
        public OrgProfile save(OrgProfile clone) {
          return accountService.saveOrgProfile(client, clone);
        }
      };
    }

  }

  public class UserIdentityAssert extends PersistableEntityAssert<UserIdentity> {
    public UserIdentityAssert(ClientInfo client, String loginId) {
      super(client, null);
      this.methods = new EntityServiceMethods() {
        public List<UserIdentity> loadList() {
          return accountService.findUserIdentity(client, loginId);
        }

        public List<UserIdentity> saveList(List<UserIdentity> identityList) {
          accountService.saveUserIdentitys(client, loginId, identityList);
          return loadList();
        }
      };
    }
  }
}
