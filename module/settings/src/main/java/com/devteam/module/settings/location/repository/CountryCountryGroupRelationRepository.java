package com.devteam.module.settings.location.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devteam.module.settings.location.entity.CountryCountryGroupRelation;

public interface CountryCountryGroupRelationRepository
    extends JpaRepository<CountryCountryGroupRelation, Serializable> {
  public void deleteByCountryId(long countryId);
  public void deleteByCountryGroupId(long countryGroupId);

  CountryCountryGroupRelation getRelationByCountryIdAndCountryGroupId(Long countryId, Long countryGroupId);
}
