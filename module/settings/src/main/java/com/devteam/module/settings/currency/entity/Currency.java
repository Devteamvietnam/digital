package com.devteam.module.settings.currency.entity;

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
  name = Currency.TABLE_NAME,
  uniqueConstraints = {
    @UniqueConstraint(
      name = Currency.TABLE_NAME + "_name",
      columnNames = { "name" }
    )
  },
  indexes = {
    @Index(columnList="name, symbol")
  }
)
@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
@Getter
@Setter
public class Currency extends BaseEntity<Long> {
  private static final long serialVersionUID = 1L;

  public static final String TABLE_NAME = "dev_settings_currency";

  @NotNull
  private String name;

  @NotNull
  private String symbol;

  private double rounding;

  @Column(name = "decimal_places")
  private int    decimalPlaces;

  private String position;

  @Column(name = "currency_unit_label")
  private String currencyUnitLabel;

  @Column(name = "ext_usd")
  private Double extUSD;

  @Column(name = "commission_ext_usd")
  private Double commissionExtUSD;

  @Column(name = "ext_vnd_sales")
  private Double extVNDSales;

  @Column(name = "commission_ext_vnd_sales")
  private Double commissionExtVNDSales;

  public Currency(String name, String symbol) {
    this.name = name;
    this.symbol = symbol;
  }

  public Currency withName(String name) {
    setName(name);
    return this;
  }

  public Currency withSymbol(String symbol) {
    setSymbol(symbol);
    return this;
  }

  public Currency withRounding(double rounding) {
    setRounding(rounding);
    return this;
  }

  public Currency withDecimalPlaces(int decimalPlaces) {
    setDecimalPlaces(decimalPlaces);
    return this;
  }

  public Currency withPosition(String position) {
    setPosition(position);
    return this;
  }

  public Currency withCurrencyUnitLabel(String currencyUnitLabel) {
    setCurrencyUnitLabel(currencyUnitLabel);
    return this;
  }

  public Currency withExtUSD(Double amount) {
    setExtUSD(amount);
    return this;
  }

  public Currency withCommissionExtUSD(Double amount) {
    setCommissionExtUSD(amount);
    return this;
  }

  public Currency withExtVNDSales(Double amount) {
    setExtVNDSales(amount);
    return this;
  }

  public Currency withCommissionExtVNDSales(Double amount) {
    setCommissionExtVNDSales(amount);
    return this;
  }
}
