package com.devteam.module.account.sample.db;

import com.devteam.module.account.entity.AccountGroup;
import com.devteam.module.account.entity.AccountType;
import com.devteam.module.account.entity.UserProfile;
import com.devteam.module.account.model.NewAccountModel;
import com.devteam.module.account.service.AccountService;
import com.devteam.module.common.ClientInfo;
import com.devteam.module.data.db.sample.EntityDB;
import com.devteam.module.util.dataformat.DataSerializer;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

public class UserData extends AccountData {
  static AccountType USER         = AccountType.USER;

  @Autowired
  private AccountService                 accountService;

  public UserProfile THIEN;

  public void initialize(ClientInfo client) {
    GroupData GROUP_DATA = EntityDB.getInstance().getData(GroupData.class);
   
    THIEN =
        new UserProfile("Thien", "Thien Dinh", "thien@devteam.com").
            withMobile("0337303666");
    THIEN = createAccount(THIEN, GROUP_DATA.ACCOUNTS);
  }

  private UserProfile createAccount(UserProfile profile, AccountGroup ... group) {
    NewAccountModel model = new NewAccountModel().withUserProfile(profile, profile.getLoginId());
    profile = accountService.createNewAccount(ClientInfo.DEFAULT, model).getUserProfile();
    for(AccountGroup sel : group) {
      accountService.createMembership(ClientInfo.DEFAULT, sel, profile.getLoginId());
    }
    return profile;
  }

  public void assertAll(ClientInfo client) throws Exception {
    UserProfile modifiedFullName = DataSerializer.JSON.clone(THIEN);
    modifiedFullName.setFullName("Thien Update");
    new UserProfileAssert(client, THIEN)
      .assertEntityUpdate()
      .assertSave(modifiedFullName, (updateProfile) -> {
        Assertions.assertEquals("Thien Update", updateProfile.getFullName());
      });
  }

}
