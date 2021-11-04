package com.devteam.module.settings.location.logic;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.devteam.module.common.ClientInfo;
import com.devteam.module.data.db.DAOService;
import com.devteam.module.data.db.entity.ChangeStorageStateRequest;
import com.devteam.module.data.db.query.EntityTable;
import com.devteam.module.data.db.query.OptionFilter;
import com.devteam.module.data.db.query.ParamFilter;
import com.devteam.module.data.db.query.RangeFilter;
import com.devteam.module.data.db.query.SearchFilter;
import com.devteam.module.data.db.query.SqlQuery;
import com.devteam.module.data.db.query.SqlQueryParams;
import com.devteam.module.settings.location.entity.Country;
import com.devteam.module.settings.location.entity.State;
import com.devteam.module.settings.location.repository.CountryRepository;
import com.devteam.module.settings.location.repository.StateRepository;
import com.devteam.util.error.ErrorType;
import com.devteam.util.error.RuntimeError;

@Component
public class StateLogic extends DAOService {
  @Autowired
  private StateRepository stateRepo;

  @Autowired
  private CountryRepository countryRepo;

 public State getState(ClientInfo clientInfo, String code) {
    return stateRepo.getByCode(code);
  }

 public State createState(ClientInfo clientInfo, State state) {
    Country country = countryRepo.getByCode(state.getCountryCode());
    if(country == null) {
      String mesg = "Country does not exist, country " + state.getCountryCode() + ", state = " + state.getLabel();
      throw new RuntimeError(ErrorType.ConstraintViolation, mesg);
    }
    state.set(clientInfo);
    return stateRepo.save(state);
  }

  public State updateState(ClientInfo clientInfo, State state) {
    state.set(clientInfo);
    return stateRepo.save(state);
  }

  public boolean changeStorageState(ClientInfo client, ChangeStorageStateRequest req) {
    List<State> states = stateRepo.findStates(req.getEntityIds());
    for(State state : states) {
      stateRepo.setStatesState(req.getNewStorageState(), state.getCode());
    }
    return true;
  }

  public List<State> findStatesInCountry(ClientInfo client, String countryCode) {
    return stateRepo.findStatesInCountry(countryCode);
  }

  public List<State> searchStates(ClientInfo client, SqlQueryParams params) {
    String[] SEARCH_FIELDS = new String[] { "code", "label"};
    SqlQuery query =
        new SqlQuery().
        ADD_TABLE(new EntityTable(State.class).selectAllFields())
        .FILTER(
             SearchFilter.isearch(State.class, SEARCH_FIELDS))
        .FILTER(new ParamFilter(State.class, "countryCode", "=", "countryCode"))
        .FILTER(
             OptionFilter.storageState(State.class),
             RangeFilter.createdTime(State.class),
             RangeFilter.modifiedTime(State.class)).
        ORDERBY(new String[] {"code", "modifiedTime"}, "modifiedTime", "ASC");
    query.mergeValue(params);
    return query(client, query, State.class);
  }
}
