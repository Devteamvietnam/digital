package com.devteam.module.settings.location.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.devteam.module.common.ClientInfo;
import com.devteam.module.data.db.sample.PersistableEntityAssert;
import com.devteam.module.data.db.sample.SampleData;
import com.devteam.module.settings.location.service.LocationService;
import com.devteam.module.settings.location.entity.City;
import com.devteam.module.settings.location.entity.Country;
import com.devteam.module.settings.location.entity.CountryCountryGroupRelation;
import com.devteam.module.settings.location.entity.CountryGroup;
import com.devteam.module.settings.location.entity.Location;
import com.devteam.module.settings.location.entity.LocationType;
import com.devteam.module.settings.location.entity.State;

public class ModuleEntityAssert extends SampleData {

  @Autowired
  LocationService service;

  public class CountryGroupAssert extends PersistableEntityAssert<CountryGroup> {
    public CountryGroupAssert(ClientInfo client, CountryGroup countryGroup) throws Exception {
      super(client, countryGroup);
      this.methods = new EntityServiceMethods() {
        public CountryGroup load() {
          return service.getCountryGroup(client, entity.getName());
        }
        public CountryGroup save(CountryGroup countryGr) {
          service.saveCountryGroup(client, countryGr);
          return load();
        }
      };
    }
  }
  public class CountryAssert extends PersistableEntityAssert<Country> {
    public CountryAssert(ClientInfo client, Country country) {
      super(client, country);
      this.methods = new EntityServiceMethods() {
        public Country load() {
          return service.getCountry(client, entity.getCode());
        }
        public Country save(Country coun) {
          service.saveCountry(client, coun);
          return load();
        }
        public List<?> searchEntity() {
          return service.searchCountries(client, createSearchQuery(country.getCode()));
        }
        public boolean archive() {
          return service.changeCountryStorageState(client, createArchivedStorageRequest(entity));
        }
      };
    }
  }
  public class StateAssert extends PersistableEntityAssert<State> {
    public StateAssert(ClientInfo client, State state) {
      super(client, state);
      this.methods = new EntityServiceMethods() {
        public State load() {
          return service.getState(client, entity.getCode());
        }
        public State save(State states) {
          service.createState(client, states);
          return load();
        }
        public List<?> searchEntity() {
          return service.searchStates(client, createSearchQuery(state.getCode()));
        }
        public boolean archive() {
          return service.changeStateStorageState(client, createArchivedStorageRequest(entity));
        }
      };
    }
  }
  public class CityAssert extends PersistableEntityAssert<City>  {
    public CityAssert(ClientInfo client, City city) {
      super(client, city);
      this.methods = new EntityServiceMethods() {
        public City load() {
          return service.loadCity(client, entity.getCode());
        }
        public City save(City citys) {
          service.saveCity(client, citys);
          return load();
        }
        public List<?> searchEntity() {
          return service.searchCities(client, createSearchQuery(city.getCode()));
        }
        public boolean archive() {
          return service.changeCityStorageState(client, createArchivedStorageRequest(entity));
        }
      };
    }
  }

  public class LocationTypeAssert extends PersistableEntityAssert<LocationType>  {
    public LocationTypeAssert(ClientInfo client, LocationType locationType) {
      super(client, locationType);
      this.methods = new EntityServiceMethods() {
        public LocationType load() {
          return service.getLocationType(client, entity.getType());
        }
        public LocationType save(LocationType locationTypes) {
          service.saveLocationType(client, locationType);
          return load();
        }
        public List<?> searchEntity() {
          return service.searchLocationType(client, createSearchQuery(locationType.getType()));
        }
        public boolean archive() {
          return service.changeLocationTypeStorageState(client, createArchivedStorageRequest(entity));
        }
      };
    }
  }

  public class LocationAssert extends PersistableEntityAssert<Location>  {
    public LocationAssert(ClientInfo client, Location location) {
      super(client, location);
      this.methods = new EntityServiceMethods() {
        public Location load() {
          return service.getLocation(client, entity.getCode());
        }
        public Location save(Location locations) {
          service.saveLocation(client, locations);
          return load();
        }
        public List<?> searchEntity() {
          return service.searchLocations(client, createSearchQuery(location.getCode()));
        }
        public boolean archive() {
          return service.changeLocationsStorageState(client, createArchivedStorageRequest(entity));
        }
      };
    }
  }

  public class CountryCountryGroupRelationAssert extends PersistableEntityAssert<CountryCountryGroupRelation> {
    public CountryCountryGroupRelationAssert(ClientInfo client, CountryGroup countryGroup, Country country) {
      this.methods = new EntityServiceMethods() {
        public CountryCountryGroupRelation load() {
          return service.createCountryGroupRelation(client, countryGroup, country);
        }
      };
    }
  }
}
