package com.devteam.digital.module.account.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPassword {
    private String oldPass;

    private String newPass;
}
