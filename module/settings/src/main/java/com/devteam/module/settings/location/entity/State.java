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
  name = State.TABLE_NAME,
  uniqueConstraints = {
    @UniqueConstraint(
      name = State.TABLE_NAME + "_code",
      columnNames = { "code", "country_code", "state_code" }
    )
  },
  indexes = { @Index(columnList="code, label") }
)
@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
@Getter @Setter
public class State extends BaseEntity<Long> implements Serializable {
  private static final long serialVersionUID = 1L;
  public static final String TABLE_NAME = "dev_settings_state";

  public static final String[] FIELDS = { "code", "label", "countryId" };

  @NotNull
  @Column(name = "country_code")
  private String countryCode;

  @NotNull
  private String code;

  @NotNull
  @Column(name = "state_code")
  private String stateCode;

  private String label;

  private String description;

  public State(String code, String name) {
    this.code      = code;
    this.stateCode = code;
    this.label = name;
    this.description = name + "[" + code + "]";
  }


  public State withStateCode(String stateCode) {
    this.stateCode = stateCode;
    return this;
  }

  public State withCountry(String countryCode) {
    this.countryCode = countryCode;
    return this;
  }

  public State withCountry(Country country) {
    this.countryCode = country.getCode();
    return this;
  }
}
