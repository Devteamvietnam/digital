package com.devteam.module.settings.location.logic;

import com.devteam.module.common.ClientInfo;
import com.devteam.module.data.db.DAOService;
import com.devteam.module.data.db.entity.ChangeStorageStateRequest;
import com.devteam.module.data.db.query.*;
import com.devteam.module.settings.location.entity.LocationAlias;
import com.devteam.module.settings.location.repository.LocationAliasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LocationAliasLogic extends DAOService {

  @Autowired
  private LocationAliasRepository aliasRepo;

  public LocationAlias getByCode(ClientInfo clientInfo, String code) {
    return aliasRepo.getByCode(code);
  }

  public LocationAlias save(ClientInfo clientInfo, LocationAlias refCode) {
    refCode.set(clientInfo);
    return aliasRepo.save(refCode);
  }

  public List<LocationAlias> search(ClientInfo client, SqlQueryParams params) {
    String[] SEARCH_FIELDS = new String[]{ "label", "code", "description" };
    SqlQuery query =
        new SqlQuery()
        .ADD_TABLE(new EntityTable(LocationAlias.class).selectAllFields())
        .FILTER( SearchFilter.isearch(LocationAlias.class, SEARCH_FIELDS))
        .FILTER(
             OptionFilter.storageState(LocationAlias.class),
             RangeFilter.modifiedTime(LocationAlias.class))
        .ORDERBY(new String[] {"code", "modifiedTime"}, "modifiedTime", "ASC");
    query.mergeValue(params);
    return query(client, query, LocationAlias.class);
  }

  public boolean changeStorageState(ClientInfo client, ChangeStorageStateRequest req) {
    List<LocationAlias> aliases = aliasRepo.findLocationAliases(req.getEntityIds());
    for(LocationAlias alias : aliases) {
      aliasRepo.setLocationAliasStorageState(req.getNewStorageState(), alias.getCode());
    }
    return true;
  }
}
