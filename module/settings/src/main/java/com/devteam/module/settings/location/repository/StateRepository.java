
package com.devteam.module.settings.location.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devteam.module.enums.StorageState;
import com.devteam.module.settings.location.entity.State;

public interface StateRepository extends JpaRepository<State, Serializable> {
  public State getByCode(String code);

  @Modifying
  @Query("UPDATE State s SET s.storageState = :state WHERE s.code = :code ")
  int setStatesState(@Param("state") StorageState state, @Param("code") String code);

  @Query("SELECT s FROM State s WHERE s.id IN :ids")
  public List<State> findStates(@Param("ids") List<Long> ids);

  @Query("SELECT s FROM State s WHERE s.countryCode IN :countryCode")
  public List<State> findStatesInCountry(@Param("countryCode") String countryCode);
}