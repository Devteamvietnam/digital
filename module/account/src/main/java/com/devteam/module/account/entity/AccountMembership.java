package com.devteam.module.account.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.devteam.module.data.db.entity.BaseEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
  name = AccountMembership.TABLE_NAME,
  indexes = {
    @Index(columnList="group_path"),
    @Index(columnList="login_id")
  }
)
@NoArgsConstructor
@Getter @Setter
public class AccountMembership extends BaseEntity<Long> {
  public static final String TABLE_NAME = "vion_account_membership";

  public static enum Status { ACTIVE, INACTIVE, SUSPENDED }

  @NotNull
  @Column(name="login_id")
  private String loginId;

  @NotNull
  @Column(name="group_path")
  private String groupPath;

  @NotNull
  private Status status = Status.ACTIVE ;

  private String role ;


  public AccountMembership(Account account, AccountGroup group) {
    this.loginId = account.getLoginId() ;
    this.groupPath = group.getPath();
  }

  public AccountMembership(String loginId, String groupPath) {
    this.loginId    =  loginId ;
    this.groupPath  = groupPath;
  }

  public AccountMembership(String loginId, String groupPath, String role) {
    this.loginId    =  loginId ;
    this.groupPath  = groupPath;
    this.role = role;
  }

  public AccountMembership withLoginId(String loginId) {
    this.loginId = loginId;
    return this;
  }
}
