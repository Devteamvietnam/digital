
package com.devteam.module.settings.location.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.devteam.module.enums.StorageState;
import com.devteam.module.settings.location.entity.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Serializable> {
  public Country getByCode(String code);

  @Query(
    "SELECT c FROM Country c, CountryCountryGroupRelation r " +
    "WHERE c.id = r.countryId AND r.countryGroupId = :countryGroupId"
  )
  public List<Country> findByCountryGroup(@Param("countryGroupId") long countryGroupId);


  @Query(
    "SELECT c FROM Country c, CountryCountryGroupRelation r " +
    " WHERE ( c.id = r.countryId AND r.countryGroupId = :countryGroupId ) AND (c.code LIKE :pattern% OR c.label LIKE :pattern%)"
  )
  public List<Country> findByCountries(@Param("countryGroupId") long countryGroupId, @Param("pattern") String pattern);

  @Query( "SELECT c FROM Country c WHERE c.code LIKE :pattern% OR c.label LIKE :pattern%")
  public List<Country> findByCountries(@Param("pattern") String pattern);

  @Query("SELECT c FROM Country c WHERE c.storageState = 'ACTIVE'")
  public List<Country> findActiveCountries();

  @Modifying
  @Query("UPDATE Country c SET c.storageState = :state WHERE c.code = :code ")
  int setCountryState(@Param("state") StorageState state, @Param("code") String code);

  @Query("SELECT c FROM Country c WHERE c.id IN :ids")
  public List<Country> findCountries(@Param("ids") List<Long> ids);

}