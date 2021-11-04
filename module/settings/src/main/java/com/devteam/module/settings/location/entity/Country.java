package com.devteam.module.settings.location.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.devteam.module.data.db.entity.BaseEntity;
import com.devteam.module.settings.currency.entity.Currency;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(
  name = Country.TABLE_NAME,
  uniqueConstraints = {
    @UniqueConstraint(
      name = Country.TABLE_NAME + "_code",
      columnNames = { "code" }
    )
  },
  indexes = { @Index(columnList="code, label") }
)
@JsonInclude(Include.NON_NULL)
@NoArgsConstructor @Getter @Setter
public class Country extends BaseEntity<Long> {
  public static final String TABLE_NAME = "dev_settings_country";

  @NotNull
  @Column(unique = true)
  private String code;

  @NotNull
  private  String label;

  private  String label2;

  private String description;

  @Column(name = "phone_code")
  private String phoneCode;

  @Column(name = "address_format")
  private String addressFormat;

  private String currency;

  public Country(String code, String name) {
    this.code = code;
    this.label = name;
    this.description = name + "[" + code + "]";
  }

  public Country(String code, String label, String addressFormat, String phoneCode) {
    this.code = code;
    this.label = label;
    this.phoneCode = phoneCode;
    this.addressFormat = addressFormat;
  }

  public Country withCode(String code) {
    setCode(code);
    return this;
  }

  public Country withLabel(String label) {
    setLabel(label);
    return this;
  }

  public Country withCurrency(Currency currency) {
    this.currency = currency.getName();
    return this;
  }

}
