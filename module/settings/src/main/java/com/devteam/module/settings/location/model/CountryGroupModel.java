package com.devteam.module.settings.location.model;

import java.util.List;

import com.devteam.module.settings.location.entity.CountryGroup;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class CountryGroupModel {
  CountryGroup countryGroup;
  List<CountryGroup> children ;

  public CountryGroupModel(CountryGroup countryGroup) {
    this.countryGroup = countryGroup;
  }

  public CountryGroupModel withChildren(List<CountryGroup> children) {
    this.children = children;
    return this;
  }
}
