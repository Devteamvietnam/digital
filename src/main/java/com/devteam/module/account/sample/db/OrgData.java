package com.devteam.module.account.sample.db;

import com.devteam.module.account.entity.AccountGroup;
import com.devteam.module.account.entity.OrgProfile;
import com.devteam.module.account.model.NewAccountModel;
import com.devteam.module.account.service.AccountService;
import com.devteam.module.common.ClientInfo;
import com.devteam.module.data.db.sample.EntityDB;
import com.devteam.module.util.dataformat.DataSerializer;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

public class OrgData extends AccountData {

  @Autowired
  AccountService     accountService;

  public OrgProfile  DEVTEAM;

  public void initialize(ClientInfo client) {
    final GroupData GROUP_DATA = EntityDB.getInstance().getData(GroupData.class);
    final UserData USER_DATA = EntityDB.getInstance().getData(UserData.class);

    DEVTEAM =  new OrgProfile("DEVTEAM")
          .withEmail("contact@devteam.com")
          .withMobile("0845505505")
          .withRepresentativeLoginId(USER_DATA.THIEN.getLoginId())
          .withRepresentative(USER_DATA.THIEN.getFullName());
    DEVTEAM = createAccount(DEVTEAM, GROUP_DATA.ACCOUNTS);

  }

  private OrgProfile createAccount(OrgProfile profile,  AccountGroup... group) {
    NewAccountModel model = new NewAccountModel().withOrgProfile(profile, profile.getLoginId());
    profile = accountService.createNewAccount(ClientInfo.DEFAULT, model).getOrgProfile();
    for (AccountGroup sel : group) {
      accountService.createMembership(ClientInfo.DEFAULT, sel, profile.getLoginId());
    }
    return profile;
  }

  public void assertAll(ClientInfo client) throws Exception {
    OrgProfile modifieldEmail = DataSerializer.JSON.clone(DEVTEAM);
    modifieldEmail.setEmail("dev@devteam.com");
    new OrgProfileAssert(client, DEVTEAM)
      .assertEntityUpdate()
      .assertSave(modifieldEmail, (updateProfile) -> {
        Assertions.assertEquals("dev@devteam.com", updateProfile.getEmail());
      });
  }
}
