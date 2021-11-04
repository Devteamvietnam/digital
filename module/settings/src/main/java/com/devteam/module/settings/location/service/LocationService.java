package com.devteam.module.settings.location.service;

import com.devteam.module.common.ClientInfo;
import com.devteam.module.data.db.entity.ChangeStorageStateRequest;
import com.devteam.module.data.db.query.SqlQueryParams;
import com.devteam.module.settings.location.entity.*;
import com.devteam.module.settings.location.logic.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LocationService {

  @Autowired
  private CountryLogic countryLogic;

  @Autowired
  private StateLogic stateLogic;

  @Autowired
  private CityLogic cityLogic;

  @Autowired
  private CountryGroupLogic countryGroupLogic;

  @Autowired
  private LocationAliasLogic locationAliasLogic;

  @Autowired
  private LocationTypeLogic locationTypeLogic;

  @Autowired
  private LocationLogic locationLogic;

  //Country group
  @Transactional(readOnly = true)
  public CountryGroup getCountryGroup(ClientInfo clientInfo, String name) {
    return countryGroupLogic.getCountryGroup(clientInfo, name);
  }

  @Transactional(readOnly = true)
  public List<CountryGroup> findCountryGroupChildren(ClientInfo clientInfo, Long groupId) {
    return countryGroupLogic.findChildren(clientInfo, groupId);
  }

  @Transactional
  public CountryGroup createCountryGroup(ClientInfo clientInfo, CountryGroup parent, CountryGroup group) {
    return countryGroupLogic.createCountryGroup(clientInfo, parent, group);
  }

  @Transactional
  public CountryGroup saveCountryGroup(ClientInfo clientInfo, CountryGroup countryGroup) {
    return countryGroupLogic.saveCountryGroup(clientInfo, countryGroup);
  }

  @Transactional
  public CountryCountryGroupRelation createCountryGroupRelation(ClientInfo clientInfo, CountryGroup countryGroup, Country country) {
    return countryGroupLogic.createCountryGroupRelation(clientInfo, countryGroup, country);
  }

  @Transactional
  public CountryCountryGroupRelation createCountryGroupRelation(ClientInfo clientInfo, CountryCountryGroupRelation relation) {
    return countryGroupLogic.saveCountryGroupRelation(clientInfo, relation);
  }

  @Transactional
  public boolean createCountryGroupRelations(ClientInfo clientInfo, Long groupId, List<Long> countryIds) {
    return countryGroupLogic.createCountryGroupRelations(clientInfo, groupId, countryIds);
  }

  @Transactional
  public boolean deleteCountryGroupRelations(ClientInfo clientInfo, Long groupId, List<Long> countryIds) {
    return countryGroupLogic.deleteCountryGroupRelations(clientInfo, groupId, countryIds);
  }

  @Transactional(readOnly = true)
  public List<CountryGroup> findCountryGroups(ClientInfo clientInfo, Country country) {
    return countryGroupLogic.findCountryGroups(clientInfo, country);
  }

  //Country
  @Transactional(readOnly = true)
  public Country getCountry(ClientInfo clientInfo, String code) {
    return countryLogic.getCountry(clientInfo, code);
  }

  @Transactional
  public Country saveCountry(ClientInfo clientInfo, Country country) {
    return countryLogic.saveCountry(clientInfo, country);
  }

  @Transactional(readOnly=true)
  public List<Country> searchCountries(ClientInfo clientInfo, String pattern) {
    return countryLogic.searchCountries(clientInfo, pattern);
  }

  @Transactional(readOnly = true)
  public List<Country> findAllCountries(ClientInfo client) {
    return countryLogic.findActiveCountries(client);
  }

  @Transactional
  public boolean changeCountryStorageState(ClientInfo client, ChangeStorageStateRequest req) {
    return countryLogic.changeStorageState(client, req);
  }

  @Transactional(readOnly = true)
  public List<Country> searchCountries(ClientInfo client, SqlQueryParams params) {
    return countryLogic.searchCountries(client, params);
  }

  //State
  @Transactional(readOnly=true)
  public State getState(ClientInfo clientInfo, String code) {
    return stateLogic.getState(clientInfo, code);
  }

  @Transactional
  public State updateState(ClientInfo clientInfo, State state) {
    return stateLogic.updateState(clientInfo, state);
  }

  @Transactional
  public State createState(ClientInfo clientInfo, State state) {
    return stateLogic.createState(clientInfo, state);
  }

  @Transactional
  public boolean changeStateStorageState(ClientInfo client, ChangeStorageStateRequest req) {
    return stateLogic.changeStorageState(client, req);
  }

  @Transactional(readOnly = true)
  public List<State> findStatesInCountry(ClientInfo client, String countryCode) {
    return stateLogic.findStatesInCountry(client, countryCode);
  }

  @Transactional(readOnly = true)
  public List<State> searchStates(ClientInfo client, SqlQueryParams params) {
    return stateLogic.searchStates(client, params);
  }

  // City
  @Transactional(readOnly = true)
  public City loadCity(ClientInfo clientInfo, String code) {
    return cityLogic.getCity(clientInfo, code);
  }

  @Transactional(readOnly = true)
  public List<City> findCitiesInState(ClientInfo client, String stateCode) {
    return cityLogic.findCitiesInState(client, stateCode);
  }

  @Transactional(readOnly = true)
  public List<City> searchCities(ClientInfo client, SqlQueryParams params) {
    return cityLogic.searchCities(client, params);
  }

  @Transactional
  public City createCity(ClientInfo clientInfo, Country country, City city) {
    return cityLogic.createCity(clientInfo, country, city);
  }

  @Transactional
  public City createCity(ClientInfo clientInfo, State state, City city) {
    return cityLogic.createCity(clientInfo, state, city);
  }

  @Transactional
  public City saveCity(ClientInfo clientInfo, City city) {
    return cityLogic.saveCity(clientInfo, city);
  }

  @Transactional
  public boolean changeCityStorageState(ClientInfo client, ChangeStorageStateRequest req) {
    return cityLogic.changeStorageState(client, req);
  }

  //Location Type
  @Transactional(readOnly=true)
  public LocationType getLocationType(ClientInfo clientInfo, String type) {
    return locationTypeLogic.getLocationType(clientInfo, type);
  }

  @Transactional
  public LocationType saveLocationType(ClientInfo clientInfo,LocationType locationType) {
    return locationTypeLogic.save(clientInfo, locationType);
  }

  @Transactional(readOnly=true)
  public List<LocationType> searchLocationType(ClientInfo clientInfo, SqlQueryParams params) {
    return locationTypeLogic.search(clientInfo, params);
  }

  @Transactional
  public boolean changeLocationTypeStorageState(ClientInfo client, ChangeStorageStateRequest req) {
    return locationTypeLogic.changeStorageState(client, req);
  }

  //Location Reference Code
  @Transactional
  public LocationAlias saveLocationAlias(ClientInfo clientInfo, LocationAlias alias) {
    return locationAliasLogic.save(clientInfo, alias);
  }

  @Transactional(readOnly=true)
  public List<LocationAlias> searchLocationAliases(ClientInfo clientInfo, SqlQueryParams params) {
    return locationAliasLogic.search(clientInfo, params);
  }

  @Transactional
  public boolean changeLocationAliasStorageState(ClientInfo client, ChangeStorageStateRequest req) {
    return locationAliasLogic.changeStorageState(client, req);
  }

  //Location
  @Transactional(readOnly=true)
  public Location getLocation(ClientInfo clientInfo, String code) {
    return locationLogic.getLocation(clientInfo, code);
  }

  @Transactional
  public Location createLocation(ClientInfo clientInfo, City city, Location location) {
    return locationLogic.createLocation(clientInfo, city, location);
  }

  @Transactional
  public Location saveLocation(ClientInfo clientInfo, Location location) {
    return locationLogic.saveLocation(clientInfo, location);
  }

  @Transactional(readOnly=true)
  public List<Location> searchLocations(ClientInfo clientInfo, SqlQueryParams params) {
    return locationLogic.searchLocations(clientInfo, params);
  }

  @Transactional
  public boolean changeLocationsStorageState(ClientInfo client, ChangeStorageStateRequest req) {
    return locationLogic.changeStorageState(client, req);
  }
}
