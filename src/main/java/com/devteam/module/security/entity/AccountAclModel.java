package com.devteam.module.security.entity;

import com.devteam.module.enums.AccessType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AccountAclModel {
    private AccessType accessType = AccessType.ACCOUNT;
    private String loginId;
    private int priority;

    private List<AppAccessPermission> appPermissions;
}
