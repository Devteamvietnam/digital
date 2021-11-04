package com.devteam.module.account.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.devteam.module.settings.location.entity.AbstractContact;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(
    name = AccountContact.TABLE_NAME,
    indexes = {
      @Index(columnList="login_id"),
    }
 )
@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
@Getter @Setter
public class AccountContact extends AbstractContact {
  private static final long serialVersionUID = 1L;
  public static final String TABLE_NAME = "dev_account_contact";

  public static enum Type { Primary, Secondary }

  @Column(name = "login_id")
  private String loginId;
  private Type   type = Type.Primary;

  public AccountContact(String loginId) {
    this.loginId = loginId;
  }

  public AccountContact(String loginId, String label) {
    this.loginId = loginId;
    this.label = label;
  }

  public AccountContact withLoginId(String loginId) {
    this.loginId = loginId;
    return this;
  }

  public AccountContact withLabel(String label) {
    super.withLabel(label);
    return this;
  }

  public AccountContact withAddress(String address) {
    super.withAddress(address);
    return this;
  }

  public AccountContact withDistrict(String district) {
    super.withDistrict(district);
    return this;
  }

  public AccountContact withCity(String city) {
    super.withCity(city);
    return this;
  }
  public AccountContact withCountry(String country) {
    super.withCountry(country);
    return this;
  }
  public AccountContact withMobile(String mobile) {
    super.withMobile(mobile);
    return this;
  }

  public AccountContact withEmail(String email) {
    super.withEmail(email);
    return this;
  }
}
