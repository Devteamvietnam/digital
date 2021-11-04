package com.devteam.module.settings.location.logic;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.devteam.module.common.ClientInfo;
import com.devteam.module.data.db.DAOService;
import com.devteam.module.data.db.entity.ChangeStorageStateRequest;
import com.devteam.module.data.db.query.ClauseFilter;
import com.devteam.module.data.db.query.EntityTable;
import com.devteam.module.data.db.query.OptionFilter;
import com.devteam.module.data.db.query.RangeFilter;
import com.devteam.module.data.db.query.SearchFilter;
import com.devteam.module.data.db.query.SqlQuery;
import com.devteam.module.data.db.query.SqlQueryParams;
import com.devteam.module.settings.location.entity.Country;
import com.devteam.module.settings.location.entity.CountryCountryGroupRelation;
import com.devteam.module.settings.location.repository.CountryRepository;

@Component
public class CountryLogic extends DAOService {
  @Autowired
  private CountryRepository countryRepo;

  public Country getCountry(ClientInfo clientInfo, String code) {
    return countryRepo.getByCode(code);
  }

  public Country saveCountry(ClientInfo clientInfo, Country country) {
    country.set(clientInfo);
    return countryRepo.save(country);
  }

  public boolean changeStorageState(ClientInfo client, ChangeStorageStateRequest req) {
    List<Country> countries = countryRepo.findCountries(req.getEntityIds());
    for(Country country : countries) {
      countryRepo.setCountryState(req.getNewStorageState(), country.getCode());
    }
    return true;
  }

  public List<Country> findActiveCountries(ClientInfo client) {
    return countryRepo.findActiveCountries();
  }

  public List<Country> searchCountries(ClientInfo client, SqlQueryParams params) {
    String[] SEARCH_FIELDS = new String[] { "code", "label"};
    SqlQuery query =
        new SqlQuery().
        ADD_TABLE(new EntityTable(Country.class).selectAllFields()).
        FILTER(
             SearchFilter.isearch(Country.class, SEARCH_FIELDS)).
        FILTER(
             OptionFilter.storageState(Country.class),
             RangeFilter.createdTime(Country.class),
             RangeFilter.modifiedTime(Country.class)).
        ORDERBY(new String[] {"code", "modifiedTime"}, "modifiedTime", "ASC");
    if(params.hasParam("groupId")) {
      query
      .ADD_TABLE(
          new EntityTable(CountryCountryGroupRelation.class))
      .FILTER(
          new ClauseFilter(CountryCountryGroupRelation.class, "countryId", "=", Country.class, "id")
          .AND(CountryCountryGroupRelation.class, "countryGroupId", "=", ":groupId"));
    }
    query.mergeValue(params);
    return query(client, query, Country.class);
  }

  public List<Country> searchCountries(ClientInfo client, String pattern) {
  SqlQueryParams params =
      new SqlQueryParams().
      FILTER(new SearchFilter("search").value(pattern));
    return searchCountries(client, params);
  }
}
