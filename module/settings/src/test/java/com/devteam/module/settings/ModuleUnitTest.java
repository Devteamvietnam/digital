package com.devteam.module.settings;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.devteam.module.common.ClientInfo;
import com.devteam.module.data.db.sample.EntityDB;
import com.devteam.module.settings.currency.data.CurrencyData;
import com.devteam.module.settings.location.data.LocationData;
import com.devteam.module.settings.resource.data.ResourceData;

public class ModuleUnitTest extends TestConfig {

  ClientInfo client = ClientInfo.DEFAULT;

  @BeforeEach
  public void setup() throws Exception {
    dbService.initDb(clientInfo, true);
  }

  @Test
  @Tag("integration")
  public void testAll() throws Exception {
    EntityDB.getInstance().getData(CurrencyData.class).assertAll(client);
    EntityDB.getInstance().getData(LocationData.class).assertAll(client);
    EntityDB.getInstance().getData(ResourceData.class).assertResourceEntity();
    EntityDB.getInstance().getData(ResourceData.class).assertResourceType();
  }

  @Test
  @Tag("unit")
  public void testCurrencyData() throws Exception {
    EntityDB.getInstance().getData(CurrencyData.class).assertAll(client);
  }

  @Test
  @Tag("unit")
  public void testLocationData() throws Exception {
    EntityDB.getInstance().getData(LocationData.class).assertAll(client);
  }

  @Test
  @Tag("unit")
  public void testResourceEntity() throws Exception {
    EntityDB.getInstance().getData(ResourceData.class).assertResourceEntity();
  }

  @Test
  @Tag("unit")
  public void testResourceType() throws Exception {
    EntityDB.getInstance().getData(ResourceData.class).assertResourceType();
  }

  @Test
  @Tag("unit") @Tag("fail")
  public void testResourceTagDelete() throws Exception {
    EntityDB.getInstance().getData(ResourceData.class).assertResourceTypeDelete();
  }
}
