package com.devteam.module.settings.currency.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.devteam.module.data.db.entity.BaseEntity;
import com.devteam.util.text.DateUtil;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
  name = CurrencyExchangeRate.TABLE_NAME,
  indexes = {
    @Index(columnList="currency")
  }
)
@JsonInclude(Include.NON_NULL)
@NoArgsConstructor @Getter @Setter
public class CurrencyExchangeRate extends BaseEntity<Long> implements Serializable {
  private static final long serialVersionUID = 1L;
  public static final String TABLE_NAME = "vion_currency_exchange_rate";

  @NotNull
  @Column(unique = true)
  private String currency;

  @Column(name = "exchange_rate")
  private double exchangeRate;

  @JsonFormat(pattern = DateUtil.COMPACT_DATETIME_FORMAT)
  @Column(name = "valid_from")
  private Date validFrom;

  @JsonFormat(pattern = DateUtil.COMPACT_DATETIME_FORMAT)
  @Column(name = "valid_to")
  private Date validTo;

  public CurrencyExchangeRate(String currency, Date validFrom, Date validTo) {
    this.currency = currency;
    this.validFrom = validFrom;
    this.validTo = validTo;
  }

  public CurrencyExchangeRate withApplyFrom(Date appliedFrom) {
    setValidFrom(appliedFrom);
    return this;
  }

  public CurrencyExchangeRate withApplyTo(Date appliedTo) {
    setValidTo(appliedTo);
    return this;
  }
}
