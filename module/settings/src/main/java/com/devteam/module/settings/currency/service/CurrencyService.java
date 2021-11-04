package com.devteam.module.settings.currency.service;

import java.util.List;

import com.devteam.module.settings.currency.logic.CurrencyLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devteam.module.common.ClientInfo;
import com.devteam.module.data.db.entity.ChangeStorageStateRequest;
import com.devteam.module.data.db.query.SqlQueryParams;
import com.devteam.module.settings.currency.entity.Currency;

@Service
public class CurrencyService {

  @Autowired
  private CurrencyLogic currencyLogic;

  @Transactional(readOnly = true)
  public Currency getCurrencyByName(ClientInfo clientInfo, String name) {
    return currencyLogic.getCurrency(clientInfo, name);
  }

  @Transactional
  public Currency saveCurrency(ClientInfo clientInfo, Currency currency) {
    return currencyLogic.saveCurrency(clientInfo, currency);
  }

  @Transactional
  public boolean changeCurrencyStorageState(ClientInfo client, ChangeStorageStateRequest req) {
    return currencyLogic.changeStorageState(client, req);
  }

  @Transactional(readOnly = true)
  public List<Currency> searchCurrencies(ClientInfo client, SqlQueryParams params) {
    return currencyLogic.searchCurrencies(client, params);
  }
}
