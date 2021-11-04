package com.devteam.module.security.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.devteam.module.common.ClientInfo;
import com.devteam.module.data.db.entity.BaseEntity;

import com.devteam.module.enums.AccessType;
import com.devteam.module.enums.Capability;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@JsonInclude(Include.NON_NULL)
@Table(
  name = AppPermission.TABLE_NAME,
  indexes = {
    @Index(columnList = "login_id")
  }
)
@NoArgsConstructor @Getter @Setter
public class AppPermission extends BaseEntity<Long>  {
  public static final String TABLE_NAME = "dev_security_app_permission";

  @ManyToOne(optional = false)
  @JoinColumn(name = "app_id")
  private App  app;

  @NotNull
  @Column(name = "login_id")
  private String     loginId;

  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(name = "access_type")
  private AccessType accessType = AccessType.dev;

  @NotNull
  @Enumerated(EnumType.STRING)
  private Capability capability;

  public AppPermission(String loginId) {
    this.loginId = loginId;
  }

    public AppPermission withCapability(Capability capability) {
    this.capability = capability;
    return this;
  }

  public AppPermission withApp(App app) {
    this.app = app;
    return this;
  }

  public AppPermission withType(AccessType type) {
    this.accessType = type;
    return this;
  }

  @Override
  public void set(ClientInfo client) {
    super.set(client);
  }
}
