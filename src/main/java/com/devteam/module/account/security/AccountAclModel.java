package com.devteam.module.account.security;

import com.devteam.core.enums.AccessType;
import com.devteam.module.security.entity.AppAccessPermission;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AccountAclModel {
    private AccessType accessType = AccessType.DEVTEAM;
    private String loginId;
    private int priority;

    private List<AppAccessPermission> appPermissions;
}
