package com.devteam.module.settings.location.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.devteam.module.data.db.entity.BaseEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
  name = City.TABLE_NAME,
  uniqueConstraints = {
    @UniqueConstraint(
      name = City.TABLE_NAME + "_code",
      columnNames = { "country_code", "state_code", "code" }
    ),
  },
  indexes = { @Index(columnList="code,label") }
)
@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
@Getter @Setter
public class City extends BaseEntity<Long> implements Serializable {
  private static final long serialVersionUID = 1L;
  public static final String TABLE_NAME = "dev_settings_city";

  final static public String[] FIELDS = { "code", "label", "countryId", "stateId" };

  @NotNull
  @Column(name = "country_code")
  private String  countryCode;

  @Column(name = "state_code")
  private String  stateCode;

  @NotNull
  @Column(unique = true)
  private String code;

  private String label;
  private String description;

  public City(String code) {
    this.code = code;
  }

  public City(String code, String name) {
    this.label = name;
    this.code = code;
    this.description = name + "[" + code + "]";
  }

  public City withCode(String code) {
    setCode(code);
    return this;
  }

  public City withLabel(String label) {
    setLabel(label);
    return this;
  }

  public City withStateCode(String stateCode) {
    this.stateCode = stateCode;
    return this;
  }

  public City withCountryCode(String countryCode) {
    this.countryCode = countryCode;
    return this;
  }

  public City withState(State state) {
    this.stateCode   = state.getCode();
    this.countryCode = state.getCountryCode();
    return this;
  }

  public City withCountry(Country country) {
    this.countryCode = country.getCode();
    return this;
  }
}
