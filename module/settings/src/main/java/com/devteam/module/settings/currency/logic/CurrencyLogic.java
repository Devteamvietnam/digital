package com.devteam.module.settings.currency.logic;

import java.util.List;

import com.devteam.module.settings.currency.model.ExchangeRate;
import com.devteam.module.settings.currency.model.ExchangeRateDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.devteam.module.common.ClientInfo;
import com.devteam.module.data.db.DAOService;
import com.devteam.module.data.db.entity.ChangeStorageStateRequest;
import com.devteam.module.data.db.query.EntityTable;
import com.devteam.module.data.db.query.OptionFilter;
import com.devteam.module.data.db.query.SearchFilter;
import com.devteam.module.data.db.query.SqlQuery;
import com.devteam.module.data.db.query.SqlQueryParams;
import com.devteam.module.settings.currency.entity.Currency;
import com.devteam.module.settings.currency.repository.CurrencyRepository;

@Component
public class CurrencyLogic extends DAOService {

  @Autowired
  private CurrencyRepository   currencyRepo;

  private ExchangeRateDB exchangeRateDB;

  public CurrencyLogic() {
    exchangeRateDB = new ExchangeRateDB();
    ExchangeRate USD  = new ExchangeRate("USD", 1, 1);
    ExchangeRate VND  = new ExchangeRate("VND", 22947.5, 22934.2);
    ExchangeRate EUR  = new ExchangeRate("EUR", 0.83, 0.825 );
    ExchangeRate YUAN = new ExchangeRate("¥",   6.4, 6.35 );
    exchangeRateDB.add(USD, VND, EUR, YUAN);
  }

  public ExchangeRateDB getExchangeRateDB() { return this.exchangeRateDB ; }

  public Currency getCurrency(ClientInfo clientInfo, Long id) {
    return currencyRepo.getById(id);
  }

  public Currency getCurrency(ClientInfo clientInfo, String name) {
    return currencyRepo.getByName(name);
  }

  public List<Currency> findAll(ClientInfo clientInfo) {
    return currencyRepo.findAll();
  }

  public Currency saveCurrency(ClientInfo clientInfo, Currency currency) {
    currency.set(clientInfo);
    return currencyRepo.save(currency);
  }

  public boolean changeStorageState(ClientInfo client, ChangeStorageStateRequest req) {
    List<Currency> currencies = currencyRepo.findCurrencies(req.getEntityIds());
    for (Currency currency : currencies) {
      currencyRepo.setCurrencyState(req.getNewStorageState(), currency.getName());
    }
    return true;
  }

  public List<Currency> searchCurrencies(ClientInfo client, SqlQueryParams params) {
    String[] SEARCH_FIELDS = new String[] { "name", "symbol"};
    SqlQuery query =
        new SqlQuery().
        ADD_TABLE(new EntityTable(Currency.class).selectAllFields()).
        FILTER(
             SearchFilter.isearch(Currency.class, SEARCH_FIELDS)).
        FILTER(
             OptionFilter.storageState(Currency.class)).
        ORDERBY(new String[] {"name", "modifiedTime"}, "modifiedTime", "DESC");
    query.mergeValue(params);
    return query(client, query, Currency.class);
  }
}
