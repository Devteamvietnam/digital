package com.devteam.module.http.rest.v1.settings.location;

import com.devteam.module.settings.location.entity.Country;
import com.devteam.module.settings.location.entity.CountryGroup;
import com.devteam.module.settings.location.entity.State;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class Params {
  @NoArgsConstructor
  @Getter @Setter
  static public class FindCityParams {
    Country country;
    State   state ;
    String  pattern;
  }

  @NoArgsConstructor
  @Getter @Setter
  static public class FindStateParams {
    Country country;
    String  pattern;
  }

  @NoArgsConstructor
  @Getter @Setter
  static public class FindCountryParams {
    CountryGroup group;
    String       pattern;
  }
}
