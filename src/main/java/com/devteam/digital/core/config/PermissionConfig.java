package com.devteam.digital.core.config;

import com.devteam.digital.core.util.SecurityUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service(value = "dev")
public class PermissionConfig {

    public Boolean check(String ...permissions){
        // Get all permissions of the current user
        List<String> elPermissions = SecurityUtils.getCurrentUser().getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        //  all permissions of the current user include the permissions defined on the interface
        return elPermissions.contains("admin") || Arrays.stream(permissions).anyMatch(elPermissions::contains);
    }
}
