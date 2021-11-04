package com.devteam.module.security;

import java.util.ArrayList;
import java.util.List;

import com.devteam.module.enums.AccessType;
import com.devteam.module.security.entity.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.devteam.module.common.ClientInfo;
import com.devteam.module.security.entity.AccessToken;
import com.devteam.module.security.entity.AppAccessPermission;
import com.devteam.module.security.entity.AppPermission;
import com.devteam.module.security.repository.AccessTokenRepository;
import com.devteam.module.security.repository.AppPermissonRepository;
import com.devteam.module.security.repository.AppRepository;
import com.devteam.module.data.db.DAOService;
import com.devteam.module.data.db.query.ClauseFilter;
import com.devteam.module.data.db.query.EntityTable;
import com.devteam.module.data.db.query.OptionFilter;
import com.devteam.module.data.db.query.ParamFilter;
import com.devteam.module.data.db.query.RangeFilter;
import com.devteam.module.data.db.query.SearchFilter;
import com.devteam.module.data.db.query.SqlQuery;
import com.devteam.module.data.db.query.SqlQueryParams;

@Component
public class SecurityLogic extends DAOService {

  @Autowired
  private AppRepository appRepo;

  @Autowired
  private AppPermissonRepository permissionRepo;

  @Autowired
  private AccessTokenRepository accessTokenRepo;

  public App getApp(ClientInfo client, String module, String name) {
    return appRepo.getApp(module, name);
  }

  public App saveApp(ClientInfo client, App app) {
    app.set(client);
    return appRepo.save(app);
  }

  public List<App> findApps(ClientInfo client) {
    return appRepo.findAll();
  }

  public AppPermission saveAppPermisson(ClientInfo client, AppPermission permission) {
    permission.set(client);
    return permissionRepo.save(permission);
  }

  public List<AppPermission> saveAppPermissions(ClientInfo client, List<AppPermission> permissions) {
    List<AppPermission> listPermission = new ArrayList<>();
    for (AppPermission sel : permissions) {
      listPermission.add(saveAppPermisson(client, sel));
    }
    return listPermission;
  }

  public List<AppPermission> findAppPermissions(ClientInfo client,String loginId) {
    return permissionRepo.findByLoginId(loginId);
  }

  public boolean deletePermissions(ClientInfo client, List<AppPermission> permissions) {
    for (AppPermission sel : permissions) {
      permissionRepo.delete(sel);
    }
    return true;
  }

  public boolean deletePermissionsByIds(ClientInfo client, List<Long> ids) {
    for (Long id : ids) {
      AppPermission appPermission = permissionRepo.getById(id);
      permissionRepo.delete(appPermission);
    }
    return true;
  }

  public List<AppAccessPermission> findPermissions(ClientInfo client, AccessType accessType, String loginId) {
    SqlQueryParams searchParams =
        new SqlQueryParams().
          addParam("loginId", loginId).
          addParam("accessType", accessType.toString());
    return searchPermissions(client, searchParams);
  }

  public List<AppAccessPermission> searchPermissions(ClientInfo client, SqlQueryParams params) {
    SqlQuery query =
        new SqlQuery()
        .ADD_TABLE(
            new EntityTable(App.class)
            .addSelectField("module", "appModule")
            .addSelectField("name", "appName"))
        .ADD_TABLE(
            new EntityTable(AppPermission.class)
            .addSelectField("id", "id")
            .addSelectField("accessType", "accessType")
            .addSelectField("capability", "capability")
            .addSelectField("loginId",    "loginId")
            .addSelectField("modifiedTime", "modifiedTime"))
        .FILTER(new ParamFilter(AppPermission.class, "app_id", "=", "appId"))
        .FILTER(new ParamFilter(AppPermission.class, "loginId", "=", "loginId"))
        .FILTER(new ParamFilter(AppPermission.class, "accessType", "=", "accessType"))
        .FILTER( new ClauseFilter(App.class, "id", "=", AppPermission.class, "app_id"))
        .FILTER(SearchFilter.isearch(AppPermission.class, new String[] {"loginId"})).
        FILTER(
            OptionFilter.storageState(AppPermission.class),
            RangeFilter.createdTime(AppPermission.class),
            RangeFilter.modifiedTime(AppPermission.class))
        .ORDERBY(new String[] {"modifiedTime" }, "modifiedTime", "DESC");
    return query(client, query, params, AppAccessPermission.class);
  }


  //Access Token

  public AccessToken getAccessToken(ClientInfo client, String token) {
    return accessTokenRepo.getByToken(token);
  }

  public AccessToken saveAccessToken(ClientInfo client, AccessToken token) {
    token.set(client);
    return accessTokenRepo.save(token);
  }

  public List<AccessToken> searchAccessTokens(ClientInfo client,SqlQueryParams params) {
    SqlQuery query =
        new SqlQuery().
        ADD_TABLE(new EntityTable(AccessToken.class).selectAllFields())
        .FILTER(SearchFilter.isearch(AccessToken.class, new String[] {"token"}))
        .FILTER(
            OptionFilter.storageState(AccessToken.class),
            RangeFilter.createdTime(AccessToken.class),
            RangeFilter.modifiedTime(AccessToken.class)).
        ORDERBY(new String[] {"modifiedTime" }, "modifiedTime", "DESC");
    return query(client, query, params, AccessToken.class);
  }
}
