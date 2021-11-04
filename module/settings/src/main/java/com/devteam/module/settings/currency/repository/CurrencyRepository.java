package com.devteam.module.settings.currency.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devteam.module.enums.StorageState;
import com.devteam.module.settings.currency.entity.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, Serializable> {
  public Currency getByName(String name);

  @Modifying
  @Query("UPDATE Currency c SET c.storageState = :state WHERE c.name = :name ")
  int setCurrencyState(@Param("state") StorageState state, @Param("name") String name);

  @Query("SELECT c FROM Currency c WHERE c.id IN :ids")
  public List<Currency> findCurrencies(@Param("ids") List<Long> ids);

}
