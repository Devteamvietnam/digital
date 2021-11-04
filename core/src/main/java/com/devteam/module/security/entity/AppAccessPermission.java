package com.devteam.module.security.entity;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.devteam.module.enums.AccessType;
import com.devteam.module.enums.Capability;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @Getter @Setter
public class AppAccessPermission  {
  private Long       id;
  private String     appModule;
  private String     appName;
  private String     loginId;
  @Enumerated(EnumType.STRING)
  private AccessType accessType = AccessType.VION;
  @Enumerated(EnumType.STRING)
  private Capability capability;
}
