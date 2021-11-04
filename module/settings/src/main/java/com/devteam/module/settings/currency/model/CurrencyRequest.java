package com.devteam.module.settings.currency.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class CurrencyRequest {

  private String name;

  public CurrencyRequest(String name) {
    this.name = name;
  }
}
