package com.devteam.module.account.model;

import com.devteam.module.account.entity.Account;
import com.devteam.module.account.entity.AccountType;
import com.devteam.module.account.entity.OrgProfile;
import com.devteam.module.account.entity.UserProfile;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter @Setter
public class NewAccountModel {
  String           loginId;
  private Account account;
  private UserProfile userProfile;
  private OrgProfile orgProfile;


  private List<String> accountGroupPaths;
  
  public NewAccountModel(String loginId) {
	    this.loginId = loginId;
	  }
  
  public NewAccountModel(Account account) {
	    this.loginId = account.getLoginId();
	    this.account = account;
}

  public NewAccountModel withAccount(Account account) {
    this.account = account;
    return this;
  }

  public NewAccountModel withUserProfile(UserProfile profile) {
    this.userProfile = profile;
    return this;
  }

  public NewAccountModel withUserProfile(UserProfile profile, String password) {
    withUserProfile(profile);
    account =
        new Account(profile.getLoginId(), password, profile.getEmail(),
            profile.getMobile(), profile.getFullName(), AccountType.USER)
        .withFullName(profile.getFullName());
    return this;
  }

  public NewAccountModel withOrgProfile(OrgProfile profile) {
    this.orgProfile = profile;
    return this;
  }

  public NewAccountModel withOrgProfile(OrgProfile profile, String password) {
    this.orgProfile = profile;
    account =
        new Account(profile.getLoginId(), password, profile.getEmail(),
            profile.getMobile(), profile.getFullName(), AccountType.ORGANIZATION)
        .withFullName(profile.getFullName());
    return this;
  }
}
