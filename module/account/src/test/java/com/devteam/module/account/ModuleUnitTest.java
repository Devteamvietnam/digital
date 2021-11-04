package com.devteam.module.account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.devteam.module.account.data.db.sample.GroupData;
import com.devteam.module.account.data.db.sample.OrgData;
import com.devteam.module.account.data.db.sample.UserData;
import com.devteam.module.common.ClientInfo;
import com.devteam.module.data.db.sample.EntityDB;

public class ModuleUnitTest extends TestConfig {

  ClientInfo clientInfo = ClientInfo.DEFAULT;

  @BeforeEach
  public void setup() throws Exception {
    dbService.initDb(clientInfo, true);
  }

  @Test
  @Tag("integration")
  public void testAll() throws Exception {
    EntityDB.getInstance().getData(GroupData.class).assertAll(clientInfo);
    EntityDB.getInstance().getData(UserData.class).assertAll(clientInfo);
    EntityDB.getInstance().getData(OrgData.class).assertAll(clientInfo);
  }

  @Test
  @Tag("unit")
  public void testAccountGroup() throws Exception {
    EntityDB.getInstance().getData(GroupData.class).assertAll(clientInfo);
  }


  @Test
  @Tag("unit")
  public void testUserProfile() throws Exception {
    EntityDB.getInstance().getData(UserData.class).assertAll(clientInfo);
  }

  @Test
  @Tag("unit")
  public void testOrgProfile() throws Exception {
    EntityDB.getInstance().getData(OrgData.class).assertAll(clientInfo);
  }
}