package com.devteam.module.security.entity;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import com.devteam.module.data.db.entity.Persistable;

import com.devteam.module.enums.Capability;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@NoArgsConstructor
@Getter @Setter
abstract public class LoginPermission extends Persistable<Long> {
  private static final long serialVersionUID = 1L;

  static public enum Type { ACCOUNT }

  @NotNull
  @Column(name="login_id")
  protected String     loginId;
  protected String     label;

  @Enumerated(EnumType.STRING)
  protected Capability capability;

  @Enumerated(EnumType.STRING)
  protected Type type ;

  public LoginPermission(String loginId, String label, Capability cap) {
    this.loginId    = loginId;
    this.label      = label;
    this.capability = cap;
  }
}
