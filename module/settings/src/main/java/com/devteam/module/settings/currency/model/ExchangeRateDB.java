package com.devteam.module.settings.currency.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.devteam.util.ds.Objects;
import lombok.Getter;

@Getter
public class ExchangeRateDB {
  private Map<String, ExchangeRate> exchangeRateMap = new HashMap<>();

  public ExchangeRateDB(ExchangeRate ... exchangeRates) {
    add(exchangeRates);
  }

  public ExchangeRateDB(List<ExchangeRate> exchangeRates) {
    for(ExchangeRate sel : exchangeRates) {
      exchangeRateMap.put(sel.getCurrency(), sel);
    }
  }

  public ExchangeRateDB add(ExchangeRate ... exchangeRates) {
    for(ExchangeRate sel : exchangeRates) {
      exchangeRateMap.put(sel.getCurrency(), sel);
    }
    return this;
  }

  public ExchangeRateDB createExchangeRateDB(String ... currencies) {
    ExchangeRateDB newDB = new ExchangeRateDB() ;
    for(String currency : currencies) {
      ExchangeRate exchangeRate = exchangeRateMap.get(currency);
      newDB.add(exchangeRate.clone());
    }
    return newDB;
  }


  public double convertBid(double amount, String fromCurrency, String toCurrency) {
    Objects.assertNotNull(fromCurrency, "From currency cannot be null");
    Objects.assertNotNull(toCurrency, "To currency cannot be null");
    ExchangeRate from = exchangeRateMap.get(fromCurrency);
    Objects.assertNotNull(from, "Cannot find the exchange rate for {} ", fromCurrency);
    ExchangeRate to = exchangeRateMap.get(toCurrency);
    Objects.assertNotNull(to, "Cannot find the exchange rate for {} ", toCurrency);
    return from.convertBid(amount, to);
  }

  public double convertAsk(double amount, String fromCurrency, String toCurrency) {
    Objects.assertNotNull(fromCurrency, "From currency cannot be null");
    Objects.assertNotNull(toCurrency, "To currency cannot be null");
    ExchangeRate from = exchangeRateMap.get(fromCurrency);
    Objects.assertNotNull(from, "Cannot find the exchange rate for {} ", fromCurrency);
    ExchangeRate to = exchangeRateMap.get(toCurrency);
    Objects.assertNotNull(to, "Cannot find the exchange rate for {} ", toCurrency);
    return from.convertAsk(amount, to);
  }

  public List<ExchangeRate> sync(List<ExchangeRate> exchangeRates) {
    for(ExchangeRate sel : exchangeRates) {
      sel.sync(exchangeRateMap.get(sel.getCurrency()));
    }
    return exchangeRates;
  }
}
