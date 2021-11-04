package com.devteam.module.settings.location.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devteam.module.settings.location.entity.CountryGroup;

public interface CountryGroupRepository extends JpaRepository<CountryGroup, Serializable> {
  public CountryGroup getByName(String name);

  @Query("SELECT cg FROM CountryGroup cg WHERE cg.parentId is Null")
  public List<CountryGroup> findRootChildren();

  @Query("SELECT cg FROM CountryGroup cg WHERE cg.parentId = :parentId")
  public List<CountryGroup> findChildren(@Param("parentId") long parentId);

  @Query(
    "SELECT cg FROM CountryGroup cg, CountryCountryGroupRelation r " +
    "WHERE cg.id = r.countryGroupId AND r.countryId = :countryId"
  )
  public List<CountryGroup> findByCountry(@Param("countryId") long countryId);

}
