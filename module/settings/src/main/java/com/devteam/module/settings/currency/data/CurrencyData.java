package com.devteam.module.settings.currency.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.devteam.module.common.ClientInfo;
import com.devteam.module.data.db.sample.PersistableEntityAssert;
import com.devteam.module.data.db.sample.SampleData;
import com.devteam.module.settings.currency.service.CurrencyService;
import com.devteam.module.settings.currency.entity.Currency;

public class CurrencyData extends SampleData {

  @Autowired
  CurrencyService service;

  public Currency VND;
  public Currency RIEL;
  public Currency USD;
  public Currency EUR;
  public Currency POUND;
  public Currency HKD;
  public Currency SGD;
  public Currency AUD;
  public Currency CHF;
  public Currency MYR;
  public Currency SEK;
  public Currency NZD;
  public Currency JPY;
  public Currency GBP;
  public Currency DKK;
  public Currency CNY;
  public Currency CAD;
  public Currency RUPIAH;
  public Currency[] ALL_CURRENCY;

  protected void initialize(ClientInfo client) {

    VND = new Currency("VND", "đ")
        .withCurrencyUnitLabel("DONG")
        .withDecimalPlaces(0)
        .withPosition("after")
        .withRounding(1000);

    RIEL = new Currency("KHR", "")
        .withCurrencyUnitLabel("Riel Cam_Pu_Chia")
        .withDecimalPlaces(0)
        .withPosition("after")
        .withRounding(0.010000);

    USD = new Currency("USD", "$")
        .withCurrencyUnitLabel("Dollars")
        .withDecimalPlaces(0)
        .withPosition("after")
        .withRounding(0.010000);

    EUR = new Currency("EUR", "€")
        .withCurrencyUnitLabel("Euros")
        .withDecimalPlaces(0)
        .withPosition("after")
        .withRounding(0.010000);

    POUND = new Currency()
        .withCurrencyUnitLabel("Euros")
        .withDecimalPlaces(0)
        .withName("CYP")
        .withPosition("after")
        .withRounding(0.010000)
        .withSymbol("£");

    HKD = new Currency("HKD", "HK$")
        .withCurrencyUnitLabel("Hong Kong Dollars")
        .withDecimalPlaces(0)
        .withPosition("after")
        .withRounding(0.010000);

    SGD = new Currency("SGD", "SG$")
        .withCurrencyUnitLabel("Singapore Dollars")
        .withDecimalPlaces(0)
        .withPosition("after")
        .withRounding(0.010000);

    AUD = new Currency("AUD", "$")
        .withCurrencyUnitLabel("Australia Dollars")
        .withDecimalPlaces(0)
        .withPosition("after")
        .withRounding(0.010000);

    CHF = new Currency("CHF", "CHF(old)")
        .withCurrencyUnitLabel("Switzerland Franc")
        .withDecimalPlaces(0)
        .withPosition("after")
        .withRounding(0.010000);

    CAD = new Currency("CAD", "$")
        .withCurrencyUnitLabel("Canada Dollars")
        .withDecimalPlaces(0)
        .withPosition("after")
        .withRounding(0.010000);

    CNY = new Currency("CNY", "¥")
        .withCurrencyUnitLabel("Chinese Yuan Renminbi")
        .withDecimalPlaces(0)
        .withPosition("after")
        .withRounding(0.010000);

    DKK = new Currency("DKK", "kr")
        .withCurrencyUnitLabel("Danish Krone")
        .withDecimalPlaces(0)
        .withPosition("after")
        .withRounding(0.010000);

    GBP = new Currency("GBP", "£")
        .withCurrencyUnitLabel("British Pound")
        .withDecimalPlaces(0)
        .withPosition("after")
        .withRounding(0.010000);

    JPY = new Currency("JPY", "¥")
        .withCurrencyUnitLabel("Japanese Yen")
        .withDecimalPlaces(0)
        .withPosition("after")
        .withRounding(0.010000);

    MYR = new Currency("MYR", "RM")
        .withCurrencyUnitLabel("Malaysian Ringgit ")
        .withDecimalPlaces(0)
        .withPosition("after")
        .withRounding(0.010000);

    NZD = new Currency("NZD", "$")
        .withCurrencyUnitLabel("New Zealand Dollar")
        .withDecimalPlaces(0)
        .withPosition("after")
        .withRounding(0.010000);

    SEK = new Currency("SEK", "Kr")
        .withCurrencyUnitLabel("Icelandic Krona ")
        .withDecimalPlaces(0)
        .withPosition("after")
        .withRounding(0.010000);

    RUPIAH = new Currency("IDR", "Rp")
        .withCurrencyUnitLabel("Rupiah Indonesia ")
        .withDecimalPlaces(0)
        .withPosition("after")
        .withRounding(0.010000);

     ALL_CURRENCY = new Currency[]{
        VND, USD, EUR, POUND, HKD, SGD, AUD, CHF , MYR , SEK , NZD , JPY , GBP, DKK , CNY , CAD, RIEL, RUPIAH
    };
    for (Currency currency : ALL_CURRENCY) {
      service.saveCurrency(client, currency);
    }
  }

  public void assertAll(ClientInfo client) throws Exception {
    new CurrencyAssert(client, VND)
      .assertEntityCreated()
      .assertEntityUpdate()
      .assertEntitySearch()
      .assertEntityArchive();
  }

  public class CurrencyAssert extends PersistableEntityAssert<Currency> {
    public CurrencyAssert(ClientInfo client, Currency currency) throws Exception {
      super(client, currency);
      this.methods = new EntityServiceMethods() {
        public Currency load() {
          return service.getCurrencyByName(client, entity.getName());
        }
        public Currency save(Currency curren) {
          service.saveCurrency(client, curren);
          return load();
        }
        public List<?> searchEntity() {
          return service.searchCurrencies(client, createSearchQuery(currency.getName()));
        }
        public boolean archive() {
          return service.changeCurrencyStorageState(client, createArchivedStorageRequest(entity));
        }
      };
    }
  }
}
