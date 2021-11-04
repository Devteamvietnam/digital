package com.devteam.module.settings.location.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class CountryGroupModelRequest {

  private String name;

  private boolean loadChildren;


  public CountryGroupModelRequest(String name) {
    this.name = name;
  }

  public CountryGroupModelRequest loadChilren() {
    this.loadChildren = true;
    return this;
  }
}
