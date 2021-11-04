package com.devteam.module.account.entity;

import java.io.Serial;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Transient;
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
  name = Account.TABLE_NAME,
  uniqueConstraints = {
    @UniqueConstraint(
        name = Account.TABLE_NAME + "_login_id",
        columnNames = {"login_id"}),
    @UniqueConstraint(
        name = Account.TABLE_NAME + "_email",
        columnNames = {"email"}),
    @UniqueConstraint(
        name = Account.TABLE_NAME + "_mobile",
        columnNames = {"mobile"}),
  },
  indexes = {
    @Index(columnList="login_id, storage_state, modified_time")
  }
)
@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
@Getter
@Setter
public class Account extends BaseEntity<Long> {

  private static final long serialVersionUID = 1L;

  public static final String TABLE_NAME = "dev_account_account";

  @NotNull
  @Column(name="login_id", unique =true)
  private String                loginId;

  @NotNull
  private String                password;

  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(name="account_type")
  private AccountType           accountType;

  private String                email;
  private String                mobile;
  @NotNull
  @Column(name="full_name")
  private String                fullName;

  @Transient
  private boolean modifiable = true;

  private int    priority = 5;

  @Column(name = "account_admin_login_id")
  private String adminAccountLoginId;

  public Account(@NotNull String loginId) {
    this.loginId = loginId;
  }

  public Account(String loginId, String password, String email, AccountType type) {
    this.loginId = loginId;
    this.fullName = loginId;
    this.password = password;
    this.email = email;
    this.accountType = type;
  }

  public Account(String loginId, String password, String email, String mobile, String fullName, AccountType type) {
    this.loginId = loginId;
    this.fullName = loginId;
    this.password = password;
    this.email = email;
    this.mobile = mobile;
    this.fullName = fullName;
    this.accountType = type;
  }

  public Account withLoginId(String loginId) {
    setLoginId(loginId);
    return this;
  }

  public Account withPassword(String password) {
    setPassword(password);
    return this;
  }

  public Account withType(AccountType type) {
    setAccountType(type);
    return this;
  }

  public Account withEmail(String email) {
    setEmail(email);
    return this;
  }

  public Account withFullName(String fullName) {
    setFullName(fullName);
    return this;
  }
  public Account withMobile(String mobile) {
    setMobile(mobile);
    return this;
  }

  public String identify() { return loginId; }
}
