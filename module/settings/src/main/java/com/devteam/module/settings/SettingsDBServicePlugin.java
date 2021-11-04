package com.devteam.module.settings;

import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.devteam.module.common.ClientInfo;
import com.devteam.module.data.db.DBScenario;
import com.devteam.module.data.db.DBServicePlugin;
import com.devteam.module.settings.currency.data.CurrencyDBModuleScenario;
import com.devteam.module.settings.location.data.LocationDBModuleScenario;
import com.devteam.module.settings.resource.data.ResourceDBModuleScenario;

@Component
@Order(value = DBServicePlugin.SETTINGS)
public class SettingsDBServicePlugin extends DBServicePlugin {
  @Override
  public void createSammpleData(ClientInfo client,  ApplicationContext context) throws Exception {
    new DBScenario(context).
      add(CurrencyDBModuleScenario.class).
      add(LocationDBModuleScenario.class).
      add(ResourceDBModuleScenario.class).
      initialize(client);
  }
}
