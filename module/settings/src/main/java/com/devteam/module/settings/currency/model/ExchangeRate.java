package com.devteam.module.settings.currency.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.devteam.util.ds.Objects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
@Getter
@Setter
public class ExchangeRate {
  private String currency;
  private double bid;
  private double ask;

  public ExchangeRate(String currency, double bid, double ask) {
    this.currency = currency;
    this.bid = bid;
    this.ask = ask;
  }

  public double convertBid(double amount, ExchangeRate other) {
    return (other.bid/bid) * amount;
  }

  public double convertAsk(double amount, ExchangeRate other) {
    return (other.ask/ask) * amount;
  }

  public void sync(ExchangeRate other) {
    Objects.assertEquals(currency, other.currency);
    bid = other.bid;
    ask = other.ask;
  }

  public ExchangeRate clone() {
    ExchangeRate newRate = new ExchangeRate(currency, bid, ask);
    return newRate;
  }
}