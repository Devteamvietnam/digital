package com.devteam.module.settings.location.data;


import com.devteam.module.common.ClientInfo;
import com.devteam.module.data.db.AbstractDBModuleScenario;
import com.devteam.module.data.db.DBScenario;
import com.devteam.module.data.db.sample.EntityDB;
import com.devteam.util.text.DateUtil;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LocationDBModuleScenario extends AbstractDBModuleScenario {
  final static DateUtil.DateRandomizer DATE_RANDOMIZER = new DateUtil.DateRandomizer("1/1/2017@00:00:00", null);


  @Override
  public void initialize(ClientInfo client, DBScenario scenario) throws Exception {
    LocationData _DATA = EntityDB.getInstance().getData(LocationData.class);
    _DATA.init(client);
  }
}
