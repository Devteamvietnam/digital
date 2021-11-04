package com.devteam.module.account.service;

import java.util.List;

import javax.annotation.PostConstruct;

import com.devteam.module.account.logic.AccountGroupLogic;
import com.devteam.module.account.logic.AccountLogic;
import com.devteam.module.account.logic.ProfileLogic;
import com.devteam.module.account.logic.UserLogic;
import com.devteam.module.account.model.ChangePasswordRequest;
import com.devteam.module.account.model.NewAccountModel;
import com.devteam.module.account.model.ResetPasswordRequest;
import com.devteam.module.account.security.AccountAclModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devteam.module.account.entity.Account;
import com.devteam.module.account.entity.AccountContact;
import com.devteam.module.account.entity.AccountGroup;
import com.devteam.module.account.entity.AccountMembership;
import com.devteam.module.account.entity.BaseProfile;
import com.devteam.module.account.entity.OrgProfile;
import com.devteam.module.account.entity.UserEducation;
import com.devteam.module.account.entity.UserIdentity;
import com.devteam.module.account.entity.UserProfile;
import com.devteam.module.account.entity.UserRelation;
import com.devteam.module.account.entity.UserWork;
import com.devteam.module.common.ClientInfo;
import com.devteam.module.common.Result;
import com.devteam.module.security.SecurityLogic;
import com.devteam.module.security.entity.AccessToken;
import com.devteam.module.data.db.entity.ChangeStorageStateRequest;
import com.devteam.module.data.db.query.SqlQueryParams;
import com.devteam.module.http.upload.UploadResource;
import com.devteam.util.error.ErrorType;
import com.devteam.util.error.RuntimeError;
import com.devteam.util.text.StringUtil;


@Service("AccountService")
public class AccountService {
  @Autowired
  private AccountLogic accountLogic;

  @Autowired
  private AccountGroupLogic groupLogic;

  @Autowired
  private ProfileLogic profileLogic;

  @Autowired
  private UserLogic userLogic;

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

  //Contact
  @Transactional(readOnly = true)
  public List<AccountContact> findAccountContact(ClientInfo clientInfo, String loginId) {
    return profileLogic.findContactByLoginId(clientInfo, loginId);
  }

  @Transactional
  public List<AccountContact> saveAccountContacts(ClientInfo clientInfo, String loginId,
      List<AccountContact> contactList) {
    return profileLogic.saveContacts(clientInfo, loginId, contactList);
  }

  @Transactional
  public AccountContact saveAccountContact(ClientInfo clientInfo, String loginId, AccountContact contact) {
    return profileLogic.saveContact(clientInfo, loginId, contact);
  }

  @Transactional
  public boolean deleteAccountContacts(ClientInfo clientInfo, String loginId, List<Long> contactIds) {
    return profileLogic.deleteContacts(clientInfo, loginId, contactIds);
  }

  // education
  @Transactional(readOnly = true)
  public List<UserEducation> findUserEducation(ClientInfo clientInfo, String loginId) {
    return userLogic.findUserEducation(clientInfo, loginId);
  }

  @Transactional
  public List<UserEducation> saveUserEducations(ClientInfo clientInfo, String loginId,
      List<UserEducation> educationList) {
    return userLogic.saveUserEducations(clientInfo, loginId, educationList);
  }

  @Transactional
  public UserEducation saveUserEducation(ClientInfo client, String loginId, UserEducation education) {
    return userLogic.saveUserEducation(client, loginId, education);
  }

  @Transactional
  public boolean deleteUserEducation(ClientInfo client, String loginId, List<Long> educationIds) {
    return userLogic.deleteUserEducation(client, loginId, educationIds);
  }

  // User Identity
  @Transactional(readOnly = true)
  public List<UserIdentity> findUserIdentity(ClientInfo clientInfo, String loginId) {
    return userLogic.findUserIdentity(clientInfo, loginId);
  }

  @Transactional
  public List<UserIdentity> saveUserIdentitys(ClientInfo clientInfo, String loginId, List<UserIdentity> identityList) {
    return userLogic.saveUserIdentitys(clientInfo, loginId, identityList);
  }

  @Transactional
  public UserIdentity saveUserIdentity(ClientInfo client, String loginId, UserIdentity identity) {
    return userLogic.saveUserIdentity(client, loginId, identity);
  }

  @Transactional
  public boolean deleteUserIdentity(ClientInfo client, String loginId, List<Long> identityIds) {
    return userLogic.deleteUserIdentity(client, loginId, identityIds);
  }

  // User Work
  @Transactional(readOnly = true)
  public List<UserWork> findUserWork(ClientInfo clientInfo, String loginId) {
    return userLogic.findUserWork(clientInfo, loginId);
  }

  @Transactional
  public List<UserWork> saveUserWorks(ClientInfo clientInfo, String loginId, List<UserWork> workList) {
    return userLogic.saveUserWorks(clientInfo, loginId, workList);
  }

  @Transactional
  public UserWork saveUserWork(ClientInfo client, String loginId, UserWork work) {
    return userLogic.saveUserWork(client, loginId, work);
  }

  @Transactional
  public boolean deleteUserWork(ClientInfo client, String loginId, List<Long> workIds) {
    return userLogic.deleteUserWork(client, loginId, workIds);
  }

  @Transactional(readOnly = true)
  public Account getAccount(ClientInfo client, String loginId) {
    return accountLogic.getModifiableAccount(client, loginId);
  }

  // User Relation
  @Transactional(readOnly = true)
  public List<UserRelation> findUserRelation(ClientInfo clientInfo, String loginId) {
    return userLogic.findUserRelations(clientInfo, loginId);
  }

  @Transactional
  public List<UserRelation> saveUserRelations(ClientInfo clientInfo, String loginId, List<UserRelation> relationList) {
    return userLogic.saveUserRelations(clientInfo, loginId, relationList);
  }

  @Transactional
  public UserRelation saveUserRelation(ClientInfo client, String loginId, UserRelation relation) {
    return userLogic.saveUserRelation(client, loginId, relation);
  }

  @Transactional
  public boolean deleteUserRelation(ClientInfo client, String loginId, List<Long> relationIds) {
    return userLogic.deleteUserRelation(client, loginId, relationIds);
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
