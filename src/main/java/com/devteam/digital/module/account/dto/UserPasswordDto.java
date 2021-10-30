package com.devteam.digital.module.account.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPasswordDto {
    private String oldPass;

    private String newPass;
}
