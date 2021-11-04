package com.devteam.module.http.rest.v1.settings.location;

import com.devteam.module.common.ClientInfo;
import com.devteam.module.data.db.entity.ChangeStorageStateRequest;
import com.devteam.module.data.db.query.SqlQueryParams;
import com.devteam.module.http.rest.RestResponse;
import com.devteam.module.http.rest.v1.BaseController;
import com.devteam.module.settings.location.service.LocationService;
import com.devteam.module.settings.location.entity.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.concurrent.Callable;

@Api(value="devteam", tags= {"settings/location"})
@RestController
@RequestMapping("/rest/vion/v1.0.0/settings/location")
public class LocationController extends BaseController {
  @Autowired
  private LocationService service;

  protected LocationController() {
    super("resource", "/location");
  }

  @ApiOperation(value = "Load the country group", response = CountryGroup.class)
  @GetMapping("country/group/{code}")
  public @ResponseBody RestResponse getCountryGroup(HttpSession session, @PathVariable("code") String code) {
    Callable<CountryGroup> executor = () -> {
      ClientInfo client = getAuthorizedClientInfo(session);
      return service.getCountryGroup(client, code);
    };
    return execute(Method.GET, "country/group/code", executor);
  }

  @ApiOperation(value = "Load the country group", responseContainer="List", response = CountryGroup.class)
  @GetMapping("country/group/children")
  public @ResponseBody RestResponse getCountryGroupChildren(HttpSession session, @RequestParam("groupId") Long groupId) {
    Callable<List<CountryGroup>> executor = () -> {
      ClientInfo client = getAuthorizedClientInfo(session);
      return service.findCountryGroupChildren(client, groupId);
    };
    return execute(Method.GET, "country/group/children", executor);
  }

  @ApiOperation(value = "Save the country group ", response = CountryGroup.class)
  @PutMapping("country/group")
  public @ResponseBody RestResponse saveCountryGroup(HttpSession session, @RequestBody CountryGroup group) {
    Callable<CountryGroup> executor = () -> {
      return service.saveCountryGroup(getAuthorizedClientInfo(session), group);
    };
    return execute(Method.PUT, "country/group", executor);
  }

  @ApiOperation(value = "Save the country group relations", response = Boolean.class)
  @PutMapping("country/group/{groupId}/relations")
  public @ResponseBody RestResponse createCountryGroupRelations(
      HttpSession session, @PathVariable("groupId") Long groupId, @RequestBody List<Long> countryIds) {
    Callable<Boolean> executor = () -> {
      service.createCountryGroupRelations(getAuthorizedClientInfo(session), groupId, countryIds);
      return true;
    };
    return execute(Method.PUT, "country/group/{groupId}/relations", executor);
  }

  @ApiOperation(value = "delete the country group relation", response = Boolean.class)
  @DeleteMapping("country/group/{groupId}/relations")
  public @ResponseBody RestResponse deleteCountryGroupRelations(
      HttpSession session, @PathVariable("groupId") Long groupId, @RequestBody List<Long> countryIds) {
    Callable<Boolean> executor = () -> {
      service.deleteCountryGroupRelations(getAuthorizedClientInfo(session), groupId, countryIds);
      return true;
    };
    return execute(Method.DELETE, "country/group/{groupId}/relations", executor);
  }

  @ApiOperation(value = "Load the country", response = Country.class)
  @GetMapping("country/{code}")
  public @ResponseBody RestResponse loadCountry(
      HttpSession session, @PathVariable("code") String code) {
    Callable<Country> executor = () -> {
      ClientInfo client = getAuthorizedClientInfo(session);
      return service.getCountry(client, code);
    };
    return execute(Method.GET, "load-country", executor);
  }

  @ApiOperation(value = "Load the country", responseContainer="list", response = State.class)
  @GetMapping("country/{code}/states")
  public @ResponseBody RestResponse findStatesInCountry(HttpSession session, @PathVariable("code") String code) {
    Callable<List<State>> executor = () -> {
      ClientInfo client = getAuthorizedClientInfo(session);
      return service.findStatesInCountry(client, code);
    };
    return execute(Method.GET, "country/{code}/states", executor);
  }

  @ApiOperation(value = "Save the country", response = Country.class)
  @PutMapping("country")
  public @ResponseBody RestResponse saveCountry(
      HttpSession session, @RequestBody Country country) {
    Callable<Country> executor = () -> {
      if(country == null) return country;
      return service.saveCountry(getAuthorizedClientInfo(session), country);
    };
    return execute(Method.PUT, "save-country", executor);
  }

  @ApiOperation(value = "find Countries ", responseContainer = "List", response = Country.class)
  @GetMapping("country/all")
  public @ResponseBody RestResponse  findAllCountries(HttpSession session) {
    Callable<List<Country>> executor = () -> {
      return service.findAllCountries(getAuthorizedClientInfo(session));
    };
    return execute(Method.POST, "country/all", executor);
  }


  @ApiOperation(value = "find Countries ", responseContainer = "List", response = Country.class)
  @GetMapping("country/search-pattern")
  public @ResponseBody RestResponse  searchCountries( HttpSession session, @RequestParam("pattern") String pattern) {
    Callable<List<Country>> executor = () -> {
      return service.searchCountries(getAuthorizedClientInfo(session), pattern);
    };
    return execute(Method.POST, "country/find", executor);
  }

  @ApiOperation(value = "Search countries ", responseContainer = "List" ,response = Country.class)
  @PostMapping("country/search")
  public @ResponseBody
      RestResponse searchCountries(HttpSession session, @RequestBody SqlQueryParams params) {
    Callable<List<Country>> executor = () -> {
      return service.searchCountries(getAuthorizedClientInfo(session), params);
    };
    return execute(Method.POST, "country/search", executor);
  }

  @ApiOperation(value = "Change the storage state", response = Boolean.class)
  @PutMapping("country/storage-state")
  public @ResponseBody RestResponse changeCountryStorageState(HttpSession session, @RequestBody ChangeStorageStateRequest req) {
    Callable<Boolean> executor = () -> {
      return service.changeCountryStorageState(getAuthorizedClientInfo(session), req);
    };
    return execute(Method.PUT, "country/storage-state", executor);
  }

  @ApiOperation(value = "Load the state", response = State.class)
  @GetMapping("state/{code}")
  public @ResponseBody RestResponse loadState(HttpSession session,  @PathVariable("code") String code) {
    Callable<State> executor = () -> {
      ClientInfo client = getAuthorizedClientInfo(session);
      return service.getState(client, code);
    };
    return execute(Method.GET, "load-state", executor);
  }

  @ApiOperation(value = "Load the state", responseContainer="List", response = City.class)
  @GetMapping("state/{code}/cities")
  public @ResponseBody RestResponse fincCitiesInState(HttpSession session,  @PathVariable("code") String code) {
    Callable<List<City>> executor = () -> {
      ClientInfo client = getAuthorizedClientInfo(session);
      return service.findCitiesInState(client, code);
    };
    return execute(Method.GET, "state/{code}/cities", executor);
  }

  @ApiOperation(value = "Save the state", response = State.class)
  @PutMapping("state")
  public @ResponseBody RestResponse saveState(HttpSession session, @RequestBody State state) {
    Callable<State> executor = () -> {
      if(state == null) return state;
      return service.createState(getAuthorizedClientInfo(session), state);
    };
    return execute(Method.PUT,"save-state", executor);
  }

  @ApiOperation(value = "Change the storage state", response = Boolean.class)
  @PutMapping("state/storage-state")
  public @ResponseBody RestResponse changeStateStorageState(HttpSession session, @RequestBody ChangeStorageStateRequest req) {
    Callable<Boolean> executor = () -> {
      return service.changeStateStorageState(getAuthorizedClientInfo(session), req);
    };
    return execute(Method.PUT, "state/storage-state", executor);
  }

  @ApiOperation(value = "Search States", responseContainer = "List", response=State.class)
  @PostMapping("state/search")
  public @ResponseBody
      RestResponse searchStates(HttpSession session, @RequestBody SqlQueryParams params) {
    Callable<List<State>> executor = () -> {
      return service.searchStates(getAuthorizedClientInfo(session), params);
    };
    return execute(Method.POST, "state/search", executor);
  }

  @ApiOperation(value = "Load the city", response = City.class)
  @GetMapping("city/{code}")
  public @ResponseBody RestResponse loadCity(HttpSession session, @PathVariable("code") String code) {
    Callable<City> executor = () -> {
      ClientInfo client = getAuthorizedClientInfo(session);
      return service.loadCity(client, code);
    };
    return execute(Method.GET, "load/city", executor);
  }

  @ApiOperation(value = "Search cities", responseContainer = "List", response = City.class)
  @PostMapping("city/search")
  public @ResponseBody
    RestResponse searchCities(HttpSession session, @RequestBody SqlQueryParams params) {
    Callable<List<City>> executor = () -> {
      return service.searchCities(getAuthorizedClientInfo(session), params);
    };
    return execute(Method.POST, "city/search", executor);
  }

  @ApiOperation(value = "Save the city", response = City.class)
  @PutMapping("city")
  public @ResponseBody RestResponse saveCityModel(HttpSession session, @RequestBody City city) {
    Callable<City> executor = () -> {
      if(city == null) return city;
      return service.saveCity(getAuthorizedClientInfo(session), city);
    };
    return execute(Method.PUT, "save-city", executor);
  }

  @ApiOperation(value = "Change the storage state", response = Boolean.class)
  @PutMapping("city/storage-state")
  public @ResponseBody RestResponse changeCityStorageState(HttpSession session, @RequestBody ChangeStorageStateRequest req) {
    Callable<Boolean> executor = () -> {
      return service.changeCityStorageState(getAuthorizedClientInfo(session), req);
    };
    return execute(Method.PUT, "city/storage-state", executor);
  }

  @ApiOperation(value = "Load the location", response = Location.class)
  @GetMapping("{code}")
  public @ResponseBody RestResponse loadLocation( HttpSession session, @PathVariable("code") String code){
    Callable<Location> executor = () -> {
      ClientInfo client = getAuthorizedClientInfo(session);
      return service.getLocation(client, code);
    };
    return execute(Method.GET, "{code}", executor);
  }

  @ApiOperation(value = "Save the location", response = Location.class)
  @PutMapping()
  public @ResponseBody RestResponse saveLocationModel(HttpSession session, @RequestBody Location location) {
    Callable<Location> executor = () -> {
      if(location == null) return location;
      return service.saveLocation(getAuthorizedClientInfo(session), location);
    };
    return execute(Method.PUT, "save-location", executor);
  }

  @ApiOperation(value = "Search Locations", responseContainer = "List", response = Location.class)
  @PostMapping("search")
  public @ResponseBody RestResponse searchLocations(HttpSession session, @RequestBody SqlQueryParams params) {
    Callable<List<Location>> executor = () -> {
      return service.searchLocations(getAuthorizedClientInfo(session), params);
    };
    return execute(Method.POST, "search-locations", executor);
  }

  @ApiOperation(value = "Change the storage state", response = Boolean.class)
  @PutMapping("storage-state")
  public @ResponseBody RestResponse changeLocationStorageState(HttpSession session, @RequestBody ChangeStorageStateRequest req) {
    Callable<Boolean> executor = () -> {
      return service.changeLocationsStorageState(getAuthorizedClientInfo(session), req);
    };
    return execute(Method.PUT, "locations/storage-state", executor);
  }

  //Location Type
  @ApiOperation(value = "Search Locations Type", responseContainer = "List", response = LocationType.class)
  @PostMapping("type/search")
  public @ResponseBody RestResponse searchLocationsType(HttpSession session, @RequestBody SqlQueryParams params) {
    Callable<List<LocationType>> executor = () -> {
      return service.searchLocationType(getAuthorizedClientInfo(session), params);
    };
    return execute(Method.POST, "search-location-type", executor);
  }

  @ApiOperation(value = "Save Location Type", response = LocationType.class)
  @PutMapping("type")
  public @ResponseBody RestResponse saveLocationType(HttpSession session, @RequestBody LocationType locationType) {
    Callable<LocationType> executor = () -> {
      if(locationType == null) return locationType;
      return service.saveLocationType(getAuthorizedClientInfo(session), locationType);
    };
    return execute(Method.PUT, "save-location-type", executor);
  }

  @ApiOperation(value = "Change Location type storage state", response = Boolean.class)
  @PutMapping("type/storage-state")
  public @ResponseBody RestResponse changeLocationTypeStorageState(HttpSession session, @RequestBody ChangeStorageStateRequest req) {
    Callable<Boolean> executor = () -> {
      return service.changeLocationTypeStorageState(getAuthorizedClientInfo(session), req);
    };
    return execute(Method.PUT, "type/locations/storage-state", executor);
  }

  //Location Reference Code
  @ApiOperation(value = "Search Location alias", responseContainer = "List", response = LocationAlias.class)
  @PostMapping("alias/search")
  public @ResponseBody RestResponse searchLocationsAliases(HttpSession session, @RequestBody SqlQueryParams params) {
    Callable<List<LocationAlias>> executor = () -> {
      return service.searchLocationAliases(getAuthorizedClientInfo(session), params);
    };
    return execute(Method.POST, "alias/search", executor);
  }

  @ApiOperation(value = "Save Location alias", response = LocationAlias.class)
  @PutMapping("alias")
  public @ResponseBody RestResponse saveLocationAlias(HttpSession session, @RequestBody LocationAlias alias) {
    Callable<LocationAlias> executor = () -> {
      if(alias == null) return alias;
      return service.saveLocationAlias(getAuthorizedClientInfo(session), alias);
    };
    return execute(Method.PUT, "alias", executor);
  }

  @ApiOperation(value = "Change Location alias storage state", response = Boolean.class)
  @PutMapping("alias/storage-state")
  public @ResponseBody RestResponse changeLocationAliasStorageState(HttpSession session, @RequestBody ChangeStorageStateRequest req) {
    Callable<Boolean> executor = () -> {
      return service.changeLocationAliasStorageState(getAuthorizedClientInfo(session), req);
    };
    return execute(Method.PUT, "alias/storage-state", executor);
  }
}