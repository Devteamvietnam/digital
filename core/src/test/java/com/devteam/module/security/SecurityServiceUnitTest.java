package com.devteam.module.security;

import java.util.List;

import com.devteam.module.security.entity.App;
import com.devteam.module.security.entity.AppAccessPermission;
import com.devteam.module.enums.Capability;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;

import com.devteam.module.common.ClientInfo;
import com.devteam.module.data.db.DBService;
import com.devteam.module.data.db.query.SqlQueryParams;

@TestPropertySource(
    locations = {"/application-test.properties"},
    properties = {
        "hibernate.hbm2ddl.auto=update",
        "hibernate.show_sql=false"
    }
    )
public class SecurityServiceUnitTest extends AbstractUnitTest {
  ClientInfo clientInfo = ClientInfo.DEFAULT;

  @Autowired
  private SecurityService service;

  @Autowired
  DBService dbService ;

  @Before
  public void setup() throws Exception {
    dbService.initDb(clientInfo, true);
  }

  @Test
  public void testSecurity() throws Exception {
    App app1 = service.getApp(clientInfo, "module1", "app1");
    Assert.assertNotNull(app1);
    Assert.assertTrue(app1.getRequiredCapability() == Capability.Admin);

    app1.withRequiredCapability(Capability.Read);
    service.saveApp(clientInfo, app1);

    App salesUpdated = service.getApp(clientInfo, "module1", "app1");
    Assert.assertNotNull(salesUpdated);
    Assert.assertEquals(Capability.Read, salesUpdated.getRequiredCapability());


    List<App> apps = service.findApps(clientInfo);
    Assert.assertTrue(apps.size() > 0);
  }

  @Test
  public void testSearchAppPermissions() throws Exception {
    SqlQueryParams searchParams = new SqlQueryParams("*");
    searchParams.addParam("appId", "1");
    searchParams.addParam("accessType", "Account");
    List<AppAccessPermission> permissions = service.searchPermissions(clientInfo, searchParams);
    Assert.assertTrue(permissions.size() > 0);
  }
}