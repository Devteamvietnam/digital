package com.devteam.module.account.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ResetPasswordRequest {
  private String loginId;
  private String email;
}
