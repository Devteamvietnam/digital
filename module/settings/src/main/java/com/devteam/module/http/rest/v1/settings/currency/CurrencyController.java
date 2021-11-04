package com.devteam.module.http.rest.v1.settings.currency;

import java.util.List;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.devteam.module.data.db.entity.ChangeStorageStateRequest;
import com.devteam.module.data.db.query.SqlQueryParams;
import com.devteam.module.http.rest.RestResponse;
import com.devteam.module.http.rest.v1.BaseController;
import com.devteam.module.settings.currency.service.CurrencyService;
import com.devteam.module.settings.currency.entity.Currency;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "devteam", tags = { "settings/currency" })
@RestController
@RequestMapping("/rest/vion/v1.0.0/settings/currency")
public class CurrencyController extends BaseController {

  @Autowired
  private CurrencyService service;

  protected CurrencyController() {
    super("resource", "/currency");
  }

  @ApiOperation(value = "Get Currency By Name", response = Currency.class)
  @GetMapping("/{name}")
  public @ResponseBody RestResponse getCurrencyByName(HttpSession session, @PathVariable("name") String name) {
    Callable<Currency> executor = () -> {
      return service.getCurrencyByName(getAuthorizedClientInfo(session), name);
    };
    return execute(Method.GET, "{name}", executor);
  }

  @ApiOperation(value = "Save the currency", response = Currency.class)
  @RequestMapping(method = RequestMethod.PUT)
  public @ResponseBody RestResponse saveCurrency(HttpSession session, @RequestBody Currency currency) {
    Callable<Currency> executor = () -> {
      if (currency == null)
        return null;
      Currency updatedCurrency = service.saveCurrency(getAuthorizedClientInfo(session), currency);
      return updatedCurrency;
    };
    return execute(Method.PUT, "", executor);
  }

  @ApiOperation(value = "Search Currencies", responseContainer = "List", response = Currency.class)
  @PostMapping("search")
  public @ResponseBody RestResponse searchCurrencies(HttpSession session, @RequestBody SqlQueryParams params) {
    Callable<List<Currency>> executor = () -> {
      return service.searchCurrencies(getAuthorizedClientInfo(session), params);
    };
    return execute(Method.POST, "search", executor);
  }

  @ApiOperation(value = "Change the storage state", response = Boolean.class)
  @PutMapping("storage-state")
  public @ResponseBody RestResponse changeStorageState(HttpSession session,
      @RequestBody ChangeStorageStateRequest req) {
    Callable<Boolean> executor = () -> {
      return service.changeCurrencyStorageState(getAuthorizedClientInfo(session), req);
    };
    return execute(Method.PUT, "storage-state", executor);
  }
}
