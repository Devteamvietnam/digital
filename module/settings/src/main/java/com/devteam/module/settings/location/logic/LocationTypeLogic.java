package com.devteam.module.settings.location.logic;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.devteam.module.common.ClientInfo;
import com.devteam.module.data.db.DAOService;
import com.devteam.module.data.db.entity.ChangeStorageStateRequest;
import com.devteam.module.data.db.query.EntityTable;
import com.devteam.module.data.db.query.OptionFilter;
import com.devteam.module.data.db.query.RangeFilter;
import com.devteam.module.data.db.query.SearchFilter;
import com.devteam.module.data.db.query.SqlQuery;
import com.devteam.module.data.db.query.SqlQueryParams;
import com.devteam.module.settings.location.entity.LocationType;
import com.devteam.module.settings.location.repository.LocationTypeRepository;

@Component
public class LocationTypeLogic extends DAOService {

  @Autowired
  private LocationTypeRepository locationTypeRepo;

  public LocationType getLocationType(ClientInfo clientInfo, String type) {
    return locationTypeRepo.getByType(type);
  }

  public LocationType save(ClientInfo clientInfo,  LocationType locationType) {
    locationType.set(clientInfo);
    return locationTypeRepo.save(locationType);
  }

  public List<LocationType> search(ClientInfo client, SqlQueryParams params) {
    String[] SEARCH_FIELDS = new String[] { "type", "label"};
    SqlQuery query =
        new SqlQuery().
        ADD_TABLE(new EntityTable(LocationType.class).selectAllFields())
        .FILTER(
             SearchFilter.isearch(LocationType.class, SEARCH_FIELDS))
        .FILTER(
             OptionFilter.storageState(LocationType.class),
             RangeFilter.createdTime(LocationType.class),
             RangeFilter.modifiedTime(LocationType.class)).
        ORDERBY(new String[] {"type", "modifiedTime"}, "modifiedTime", "ASC");
    query.mergeValue(params);
    return query(client, query, LocationType.class);
  }


  public boolean changeStorageState(ClientInfo client, ChangeStorageStateRequest req) {
    List<LocationType> locationTypes = locationTypeRepo.findLocationTypes(req.getEntityIds());
    for(LocationType locationType : locationTypes) {
      locationTypeRepo.setLocationTypeStorageState(req.getNewStorageState(), locationType.getType());
    }
    return true;
  }
}
