package com.devteam.module.account.sample.db;

import com.devteam.module.common.ClientInfo;
import com.devteam.module.data.db.AbstractDBModuleScenario;
import com.devteam.module.data.db.DBScenario;
import com.devteam.module.data.db.sample.EntityDB;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DBModuleScenario extends AbstractDBModuleScenario {
  public void initialize(ClientInfo client, DBScenario scenario) throws Exception {
    EntityDB.getInstance().getData(GroupData.class).initialize(client);
    EntityDB.getInstance().getData(OrgData.class).initialize(client);
    EntityDB.getInstance().getData(UserData.class).initialize(client);
  }
}