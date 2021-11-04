package com.devteam.module.account.security;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.devteam.module.security.entity.AccessToken;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @Getter @Setter
public class ACLModel {
    private String                sessionId;
    private AccessToken           accessToken;
    private AccountAclModel       accountAcl;
    private List<AccountAclModel> availableAccountAcls;

    public ACLModel(AccessToken accessToken) {
        this.accessToken = accessToken;
    }

    @JsonIgnore
    public boolean isSuperUser() { return "admin".equals(accessToken.getLoginId()) ; }

    public ACLModel withAcessToken(AccessToken token) {
        this.accessToken = token;
        return this;
    }

    public ACLModel withAvailableAccountAcls(List<AccountAclModel> acls) {
        this.availableAccountAcls = acls;
        if(acls.size() > 0) {
            this.accountAcl = acls.get(0);
        }
        return this;
    }
}
