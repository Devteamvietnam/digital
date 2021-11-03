package com.devteam.module.security.entity;

import com.devteam.module.enums.AccessType;
import com.devteam.module.enums.Capability;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@NoArgsConstructor @Getter @Setter
public class AppAccessPermission  {
  private Long       id;
  private String     appModule;
  private String     appName;
  private String     loginId;
  @Enumerated(EnumType.STRING)
  private AccessType accessType = AccessType.devteam;
  @Enumerated(EnumType.STRING)
  private Capability capability;
}
