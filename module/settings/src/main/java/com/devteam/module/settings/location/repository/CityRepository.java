package com.devteam.module.settings.location.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devteam.module.enums.StorageState;
import com.devteam.module.settings.location.entity.City;

public interface CityRepository extends JpaRepository<City, Serializable> {
  public City getByCode(String code);

  @Modifying
  @Query("UPDATE City c SET c.storageState = :state WHERE c.code = :code ")
  int setCitiesState(@Param("state") StorageState state, @Param("code") String code);

  @Query("SELECT c FROM City c WHERE c.id IN :ids")
  public List<City> findCities(@Param("ids") List<Long> ids);

  @Query("SELECT c FROM City c WHERE c.stateCode IN :stateCode")
  public List<City> findCitiesInState(@Param("stateCode") String stateCode);
}
