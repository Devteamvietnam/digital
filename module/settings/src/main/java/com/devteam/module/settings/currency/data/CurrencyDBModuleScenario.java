package com.devteam.module.settings.currency.data;

import com.devteam.module.common.ClientInfo;
import com.devteam.module.data.db.AbstractDBModuleScenario;
import com.devteam.module.data.db.DBScenario;
import com.devteam.module.data.db.sample.EntityDB;
import com.devteam.util.text.DateUtil;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CurrencyDBModuleScenario extends AbstractDBModuleScenario {
  final static DateUtil.DateRandomizer DATE_RANDOMIZER = new DateUtil.DateRandomizer("15/10/20201@00:00:00", null);

  @Override
  public void initialize(ClientInfo client, DBScenario scenario) throws Exception {
    CurrencyData _DATA = EntityDB.getInstance().getData(CurrencyData.class);
    _DATA.init(client);
  }
}
