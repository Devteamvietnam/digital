package com.devteam.module.account.service;

import com.devteam.module.account.entity.*;
import com.devteam.module.account.logic.AccountGroupLogic;
import com.devteam.module.account.logic.AccountLogic;
import com.devteam.module.account.logic.ProfileLogic;
import com.devteam.module.account.model.ChangePasswordRequest;
import com.devteam.module.account.model.NewAccountModel;
import com.devteam.module.account.model.ResetPasswordRequest;
import com.devteam.module.account.entity.AccountAclModel;
import com.devteam.module.common.ClientInfo;
import com.devteam.module.common.Result;
import com.devteam.module.data.db.entity.ChangeStorageStateRequest;
import com.devteam.module.data.db.query.SqlQueryParams;
import com.devteam.module.http.upload.UploadResource;
import com.devteam.module.security.SecurityLogic;
import com.devteam.module.security.entity.AccessToken;
import com.devteam.module.util.error.ErrorType;
import com.devteam.module.util.error.RuntimeError;
import com.devteam.module.util.text.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;


@Service("AccountService")
public class AccountService {
  @Autowired
  private AccountLogic accountLogic;

  @Autowired
  private AccountGroupLogic groupLogic;

  @Autowired
  private ProfileLogic profileLogic;

  @Autowired
  private SecurityLogic securityLogic;

  @PostConstruct
  public void onInit() {
  }

  @Transactional
  public AccessToken authenticate(ClientInfo client, String loginId, String password, int liveTimeInMin) {
    Account account = accountLogic.authenticate(client, loginId, password);
    if(account == null) {
      return new AccessToken("anon", AccessToken.AccessType.None);
    }
    AccessToken token =
        new AccessToken(null, AccessToken.AccessType.Account)
        .withLabel(account.getFullName())
        .withLoginId(loginId)
        .withGenToken()
        .withLiveTime(liveTimeInMin);
    token = securityLogic.saveAccessToken(client, token);
    return token;
  }


  @Transactional(readOnly = true)
  public Account getAccount(ClientInfo client, String loginId) {
    return accountLogic.getModifiableAccount(client, loginId);
  }

  @Transactional(readOnly = true)
  public Account getAccountById(ClientInfo clientInfo, String loginId) {
    return accountLogic.getAccountById(clientInfo, loginId);
  }

  @Transactional(readOnly = true)
  public List<AccountAclModel> findAccountAcls(ClientInfo client, String loginId) {
    return accountLogic.findAccountAcls(client, loginId);
  }

  // Account Group
  @Transactional(readOnly = true)
  public AccountGroup getAccountGroup(ClientInfo client, String path) {
    return groupLogic.getAccountGroup(client, path);
  }

  @Transactional(readOnly = true)
  public List<AccountGroup> findAccountGroupChildren(ClientInfo client, String parentPath) {
    if(StringUtil.isEmpty(parentPath)) parentPath = null;
    return groupLogic.findChildren(client, parentPath);
  }
  @Transactional
  public AccountGroup saveAccountGroup(ClientInfo client, AccountGroup group) {
    return groupLogic.saveAccountGroup(client, group);
  }

  @Transactional
  public AccountGroup createAccountGroup(ClientInfo client, Long parentId, AccountGroup group) {
    return groupLogic.createAccountGroup(client, parentId, group);
  }

  @Transactional
  public AccountGroup updateAccountGroup(ClientInfo client, AccountGroup group) {
    return groupLogic.updateAccountGroup(client, group);
  }

  @Transactional
  public Boolean deleteAccountGroup(ClientInfo client, Long id) {
    return groupLogic.delete(client, id);
  }

  @Transactional
  public AccountMembership createMembership(ClientInfo client, AccountGroup group, String loginId) {
    return accountLogic.createMembership(client, group, loginId);
  }

  @Transactional
  public boolean createAccuntMemberships(ClientInfo clientInfo, Long groupId, List<String> accountLoginIds) {
    return groupLogic.createAccountMemberships(clientInfo, groupId, accountLoginIds);
  }

  @Transactional
  public boolean deleteAccountMemberships(ClientInfo clientInfo, Long groupId, List<String> accountLoginIds) {
    return groupLogic.deleteAccountMemberships(clientInfo, groupId, accountLoginIds);
  }

  //Account
  @Transactional
  public NewAccountModel createNewAccount(ClientInfo client, NewAccountModel model) {
    return accountLogic.createNewAccount(client, model);
  }

  @Transactional
  public Account saveAccount(ClientInfo client, Account account) {
    if(account.isNew()) {
      throw new RuntimeError(ErrorType.IllegalArgument, "Expect a created account");
    }
    return accountLogic.updateAccount(client, account);
  }

  @Transactional
  public boolean changeAccountStorageState(ClientInfo client, ChangeStorageStateRequest req) {
    return accountLogic.changeStorageState(client, req);
  }

  @Transactional(readOnly = true)
  public  List<Account> searchAccounts(ClientInfo client, SqlQueryParams params) {
    return accountLogic.searchAccounts(client, params);
  }

  //Profile

  public <T extends BaseProfile> T getProfile(ClientInfo clientInfo, String loginId) {
    return profileLogic.getProfile(clientInfo, loginId);
  }

  public UserProfile saveUserProfile(ClientInfo clientInfo, UserProfile profile) {
    return profileLogic.saveUserProfile(clientInfo, profile);
  }

  public OrgProfile saveOrgProfile(ClientInfo clientInfo, OrgProfile profile) {
    return profileLogic.saveOrgProfile(clientInfo, profile);
  }


  public UploadResource uploadAvatar(ClientInfo client, String loginId, UploadResource resource, boolean saveOrigin) {
    return profileLogic.uploadAvatar(client, loginId, resource, saveOrigin);
  }

  // Account password
  @Transactional
  public Result<Boolean> changePassword(ClientInfo client, ChangePasswordRequest request) {
    return accountLogic.changePassword(client, request);
  }
  @Transactional
  public Result<Boolean> resetPassword(ClientInfo client, ResetPasswordRequest request) {
    return accountLogic.resetPassword(client, request);
  }
}
