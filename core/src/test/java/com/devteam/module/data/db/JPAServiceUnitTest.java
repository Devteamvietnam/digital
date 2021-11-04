package com.devteam.module.data.db;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.devteam.module.common.ClientInfo;
import com.devteam.module.data.TestConfig;
import com.devteam.util.dataformat.DataSerializer;

public class JPAServiceUnitTest extends TestConfig {
  @Autowired
  private JPAService service;

  @Test @Tag("unit")
  public void test() {
    List<EntityInfo> entities = service.getEntityInfos(ClientInfo.DEFAULT);

    for(EntityInfo sel : entities) {
      System.out.println(DataSerializer.JSON.toString(sel));
    }
  }
}
