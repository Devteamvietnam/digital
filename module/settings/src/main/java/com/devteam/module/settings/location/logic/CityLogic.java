package com.devteam.module.settings.location.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.devteam.module.common.ClientInfo;
import com.devteam.module.data.db.DAOService;
import com.devteam.module.data.db.entity.ChangeStorageStateRequest;
import com.devteam.module.data.db.query.EntityTable;
import com.devteam.module.data.db.query.OptionFilter;
import com.devteam.module.data.db.query.ParamFilter;
import com.devteam.module.data.db.query.RangeFilter;
import com.devteam.module.data.db.query.SearchFilter;
import com.devteam.module.data.db.query.SqlQuery;
import com.devteam.module.data.db.query.SqlQueryParams;
import com.devteam.module.settings.location.entity.City;
import com.devteam.module.settings.location.entity.Country;
import com.devteam.module.settings.location.entity.State;
import com.devteam.module.settings.location.repository.CityRepository;

@Component
public class CityLogic extends DAOService {
  @Autowired
  private CityRepository cityRepo;

  public City getCity(ClientInfo clientInfo, String code) {
    return cityRepo.getByCode(code);
  }

  public City createCity(ClientInfo clientInfo, Country country, City city) {
    city.withCountry(country);
    city.set(clientInfo);
    return cityRepo.save(city);
  }


  public City createCity(ClientInfo clientInfo, State state, City city) {
    city.withState(state);
    city.set(clientInfo);
    city = cityRepo.save(city);
    return city;
  }

  public City saveCity(ClientInfo clientInfo, City city) {
    city.set(clientInfo);
    return cityRepo.save(city);
  }

  public boolean changeStorageState(ClientInfo client, ChangeStorageStateRequest req) {
    List<City> cities = cityRepo.findCities(req.getEntityIds());
    for(City city : cities) {
      cityRepo.setCitiesState(req.getNewStorageState(), city.getCode());
    }
    return true;
  }

  public List<City> findCitiesInState(ClientInfo client, String stateCode) {
    return cityRepo.findCitiesInState(stateCode);
  }

  public List<City> searchCities(ClientInfo client, SqlQueryParams params) {
    String[] SEARCH_FIELDS = new String[] { "code", "label"};
    SqlQuery query =
        new SqlQuery().
        ADD_TABLE(new EntityTable(City.class).selectAllFields()).
        FILTER(
             SearchFilter.isearch(City.class, SEARCH_FIELDS))
        .FILTER(new ParamFilter(City.class, "countryCode", "=", "countryCode"))
        .FILTER(new ParamFilter(City.class, "stateCode", "=", "stateCode"))
        .FILTER(
             OptionFilter.storageState(City.class),
             RangeFilter.createdTime(City.class),
             RangeFilter.modifiedTime(City.class)).
        ORDERBY(new String[] {"code", "modifiedTime"}, "modifiedTime", "ASC");
    query.mergeValue(params);
    return query(client, query, City.class);
  }
}
