package com.devteam.module.settings.location.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.devteam.module.data.db.entity.BaseEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
  name = CountryCountryGroupRelation.TABLE_NAME
)
@JsonInclude(Include.NON_NULL)
@NoArgsConstructor @Getter @Setter
public class CountryCountryGroupRelation extends BaseEntity<Long> {
  private static final long serialVersionUID = 1L;

  public static final String TABLE_NAME = "dev_settings_country_group_rel";

  @NotNull
  @Column(name = "country_id")
  private Long countryId;

  @NotNull
  @Column(name = "country_group_id")
  private Long countryGroupId;

  public CountryCountryGroupRelation(long countryId, long countryGroupId) {
    this.countryId      = countryId;
    this.countryGroupId = countryGroupId;
  }

  public CountryCountryGroupRelation(CountryGroup countryGroup, Country country) {
    this.countryId         = country.getId();
    this.countryGroupId    = countryGroup.getId();
  }
}
