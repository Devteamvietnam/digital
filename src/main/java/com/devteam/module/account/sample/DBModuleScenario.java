package com.devteam.module.account.sample;

import com.devteam.common.ClientInfo;
import com.devteam.core.data.db.AbstractDBModuleScenario;
import com.devteam.core.data.db.DBScenario;
import com.devteam.core.data.db.sample.EntityDB;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DBModuleScenario extends AbstractDBModuleScenario {
  public void initialize(ClientInfo client, DBScenario scenario) throws Exception {
    EntityDB.getInstance().getData(GroupData.class).initialize(client);
    EntityDB.getInstance().getData(UserData.class).initialize(client);
    EntityDB.getInstance().getData(OrgData.class).initialize(client);
  }
}