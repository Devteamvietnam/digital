package com.devteam.module.settings.resource.data;


import com.devteam.module.common.ClientInfo;
import com.devteam.module.data.db.AbstractDBModuleScenario;
import com.devteam.module.data.db.DBScenario;
import com.devteam.module.data.db.sample.EntityDB;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ResourceDBModuleScenario extends AbstractDBModuleScenario {

  @Override
  public void initialize(ClientInfo client, DBScenario scenario) throws Exception {
    EntityDB.getInstance().getData(ResourceData.class).init(client);
  }
}
