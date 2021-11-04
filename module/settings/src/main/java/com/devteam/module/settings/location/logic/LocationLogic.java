package com.devteam.module.settings.location.logic;

import com.devteam.module.common.ClientInfo;
import com.devteam.module.data.db.DAOService;
import com.devteam.module.data.db.entity.ChangeStorageStateRequest;
import com.devteam.module.data.db.query.*;
import com.devteam.module.settings.location.entity.*;
import com.devteam.module.settings.location.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LocationLogic extends DAOService {
  @Autowired
  private LocationRepository locationRepo;

  public Location getLocation(ClientInfo clientInfo, String code) {
    return locationRepo.getByCode(code);
  }

  public Location createLocation(ClientInfo client, City city, Location location) {
    location.withCity(city);
    location.set(client);
    return locationRepo.save(location);
  }

  public Location createLocation(ClientInfo client, State state, Location location) {
    location.withState(state);
    location.set(client);
    return locationRepo.save(location);
  }

  public Location createLocation(ClientInfo client, Country country, Location location) {
    location.withCountry(country);
    location.set(client);
    return locationRepo.save(location);
  }

  public Location saveLocation(ClientInfo clientInfo, Location location) {
    location.set(clientInfo);
    return locationRepo.save(location);
  }

  public List<Location> searchLocations(ClientInfo client, SqlQueryParams params) {
    String[] SEARCH_FIELDS = new String[] { "code", "label" };
    SqlQuery query = new SqlQuery().ADD_TABLE(new EntityTable(Location.class).selectAllFields())
      .FILTER(SearchFilter.isearch(Location.class, SEARCH_FIELDS))
      .FILTER(new ParamFilter(Location.class, "cityCode", "=", "cityCode"))
      .FILTER(new ParamFilter(Location.class, "stateCode", "=", "stateCode"))
      .FILTER(new ParamFilter(Location.class, "countryCode", "=", "countryCode"))
      .FILTER(
        OptionFilter.storageState(Location.class),
        RangeFilter.createdTime(Location.class),
        RangeFilter.modifiedTime(Location.class))
      .ORDERBY(new String[] { "code", "modifiedTime" }, "modifiedTime", "ASC");

    if(params.hasParam("alias")) {
      query.JOIN(
        new Join("JOIN", LocationAlias.class)
          .ON("location_id", Location.class, "id"))
        .FILTER(new ClauseFilter(LocationAlias.class, "code", "=", ":alias"));
    }

    return query(client, query, params, Location.class);
  }

  public boolean changeStorageState(ClientInfo client, ChangeStorageStateRequest req) {
    List<Location> locations = locationRepo.findLocations(req.getEntityIds());
    for(Location location : locations) {
      locationRepo.setLocationState(req.getNewStorageState(), location.getCode());
    }
    return true;
  }
}
