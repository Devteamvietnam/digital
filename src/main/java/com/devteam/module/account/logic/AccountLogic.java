package com.devteam.module.account.logic;

import com.devteam.common.ClientInfo;
import com.devteam.common.Result;
import com.devteam.core.data.cache.CachingConfig;
import com.devteam.core.data.db.DAOService;
import com.devteam.core.data.db.JPAService;
import com.devteam.core.data.db.entity.ChangeStorageStateRequest;
import com.devteam.core.data.db.query.*;
import com.devteam.core.enums.StorageState;
import com.devteam.lib.util.avatar.AvatarUtil;
import com.devteam.lib.util.ds.Objects;
import com.devteam.lib.util.error.ErrorType;
import com.devteam.lib.util.error.RuntimeError;
import com.devteam.lib.util.text.StringUtil;
import com.devteam.module.account.entity.*;
import com.devteam.module.account.model.ChangePasswordRequest;
import com.devteam.module.account.model.NewAccountModel;
import com.devteam.module.account.model.PasswordGenerator;
import com.devteam.module.account.model.ResetPasswordRequest;
import com.devteam.module.account.plugin.AccountServicePlugin;
import com.devteam.module.account.repository.AccountGroupRepository;
import com.devteam.module.account.repository.AccountRepository;
import com.devteam.module.account.repository.OrgProfileRepository;
import com.devteam.module.account.repository.UserProfileRepository;
import com.devteam.module.account.security.AccountAclModel;
import com.devteam.module.storage.IStorageService;
import com.devteam.module.storage.StorageResource;
import com.devteam.module.storage.UserStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class AccountLogic extends DAOService {
  @Autowired
  private AccountRepository accountRepo;

  @Autowired
  private UserProfileRepository userProfileRepo;

  @Autowired
  private OrgProfileRepository orgProfileRepo;

  @Autowired
  private AccountGroupRepository accountGroupRepo;

  @Autowired
  private IStorageService storageService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private PasswordGenerator passwordGenerator;

  @Autowired
  private JPAService jpaService;

  @Autowired(required = false)
  List<AccountServicePlugin> plugins = new ArrayList<>();

  public Account authenticate(ClientInfo client, String loginId, String password) {
    System.out.println("authenticate '" + loginId + "', password = '" + password + "'");
    Account account   = getModifiableAccount(client, loginId);
    if (account != null) {
      if(passwordEncoder.matches(password, account.getPassword())) {
        return account;
      }
    }
    return null;
  }

  @Cacheable(value = CachingConfig.REGION_ENTITY , key= "{'" + Account.TABLE_NAME + "', #loginId}")
  public Account getAccount(ClientInfo client, String loginId) {
    Account account = accountRepo.getByLoginId(loginId);
    if(account != null) {
      jpaService.getEntityManager().detach(account);
      account.setPassword(null);
      account.setModifiable(false);
    }
    return account;
  }

  public Account getAccountById(ClientInfo clientInfo,  String loginId) {
    return accountRepo.getAccountByLoginId(loginId);
  }

  public Account getModifiableAccount(ClientInfo client, String loginId) {
    Account account = accountRepo.getByLoginId(loginId);
    return account;
  }

  public Account save(Account account) {
    return accountRepo.save(account);
  }

  @CacheEvict(value = CachingConfig.REGION_ENTITY , key= "{'" + Account.TABLE_NAME + "', #account.loginId}")
  public Account updateAccount(ClientInfo client, Account account) {
    if(!account.isModifiable()) {
      throw new RuntimeError(ErrorType.IllegalArgument, "Account is not modifiable");
    }
    final String   loginId   = account.getLoginId();

    account.setPassword(passwordEncoder.encode(account.getPassword()));

    for(AccountServicePlugin plugin : plugins) {
      plugin.onPreSave(client, account, false);
    }

    if(account.getAccountType() == AccountType.USER) {
      UserProfile profile = userProfileRepo.getByLoginId(loginId);
      profile.setEmail(account.getEmail());
      profile.setMobile(account.getMobile());
      profile.setFullName(account.getFullName());
      userProfileRepo.save(profile);
    } else {
      OrgProfile profile = orgProfileRepo.getByLoginId(loginId);
      profile.setEmail(account.getEmail());
      profile.setMobile(account.getMobile());
      profile.setFullName(account.getFullName());
      orgProfileRepo.save(profile);
    }
    account.set(client);
    account = accountRepo.save(account);

    for(AccountServicePlugin plugin : plugins) {
      plugin.onPostSave(client, account, false);
    }
    return account;
  }

  public NewAccountModel createNewAccount(ClientInfo client, NewAccountModel model) {
    Account account = model.getAccount();
    boolean isNew = account.isNew();

    for(AccountServicePlugin plugin : plugins) {
      plugin.onPreSave(client, account, isNew);
    }
    account.set(client);
    if(StringUtil.isEmpty(account.getMobile())) {
      account.setMobile(null);
    }
    account.setPassword(passwordEncoder.encode(account.getPassword()));
    account = accountRepo.save(account);

    NewAccountModel retModel = new NewAccountModel();
    retModel.withAccount(account);
    UserStorage storage = storageService.createUserStorage(client, account.getLoginId());
    String fullName = account.getFullName();
    if(fullName == null) {
      fullName = account.getLoginId();
    } else {
      fullName = fullName.trim();
      if(StringUtil.isEmpty(fullName)) fullName = account.getLoginId();
    }
    byte[] avatarBytes = AvatarUtil.createPngAsBytes(150, 150, fullName);
    StorageResource origAvatarResource = new StorageResource("orig-avatar.png", avatarBytes);
    origAvatarResource = storage.wwwSave("avatar", origAvatarResource);
    StorageResource avatarResource = new StorageResource("avatar.png", avatarBytes);
    avatarResource = storage.wwwSave("avatar", avatarResource);
    if(account.getAccountType() == AccountType.USER) {
      UserProfile profile = model.getUserProfile();
      if(Objects.isNull(profile)) {
        profile = new UserProfile(account.getLoginId(), account.getFullName(), account.getEmail());
        profile.setMobile(account.getMobile());
      }
      profile.setAvatarUrl(avatarResource.getPublicDownloadUri());
      profile.set(client);
      profile = userProfileRepo.save(profile);
      retModel.withUserProfile(profile);
    } else {
      OrgProfile profile = model.getOrgProfile();
      if(Objects.isNull(profile)) {
        profile = new OrgProfile(account.getLoginId(), account.getFullName(), account.getEmail());
      }
      profile.setAvatarUrl(avatarResource.getPublicDownloadUri());
      profile.set(client);
      profile = orgProfileRepo.save(profile);
      retModel.withOrgProfile(profile);
    }
    for(AccountServicePlugin plugin : plugins) {
      plugin.onPostSave(client, account, isNew);
    }

    return retModel;
  }

  @CacheEvict(value = CachingConfig.REGION_ENTITY , key= "{'" + Account.TABLE_NAME + "', #request.loginId}")
  public Result<Boolean> changePassword(ClientInfo client, ChangePasswordRequest request) {
    log.debug("Call change password");
    Account account   = getModifiableAccount(client, request.getLoginId());
    if (account != null) {
      if(passwordEncoder.matches(request.getOldPassword(), account.getPassword())) {
        account.setPassword(passwordEncoder.encode(request.getNewPassword()));
        account.set(client);
        account = accountRepo.save(account);
        return new Result<>(Result.Status.Success, true).withMessage("Change password successfully!");
      }
      return new Result<>(Result.Status.Fail, true).withMessage("The old password is not matched");
    }
    return new Result<>(Result.Status.Fail, false).withMessage("Cannot find the account");
  }

  public Result<Boolean> resetPassword(ClientInfo client, ResetPasswordRequest request) {
    Account account   = getModifiableAccount(client, request.getLoginId());
    if(account != null) {
      String newPassword = passwordGenerator.generatePassword(8);
      account.setPassword(newPassword);
      String msg = "A new password is generated successfully. The new password is " + newPassword;
      return new Result<>(Result.Status.Success, true).withMessage(msg);
    }
    return new Result<>(Result.Status.Fail, false).withMessage("Cannot find the account");
  }

  public NewAccountModel loadAccountModel(ClientInfo client, String loginId) {
    //TODO: clear password
    Account account = accountRepo.getByLoginId(loginId);
    if(account == null) return null;

    account.setPassword(null);
    NewAccountModel model = new NewAccountModel(account);

    if(account.getAccountType() == AccountType.USER) {
      UserProfile uProfile = userProfileRepo.getByLoginId(loginId);
      if(uProfile == null) uProfile = new UserProfile(loginId);
      model.setUserProfile(uProfile);
    } else {
      OrgProfile orgProfile = orgProfileRepo.getByLoginId(loginId);
      if(orgProfile == null) orgProfile = new OrgProfile(loginId);
      model.setOrgProfile(orgProfile);
    }
    return model;
  }




  public boolean changeStorageState(ClientInfo client, ChangeStorageStateRequest req) {
    List<Account> accounts = accountRepo.findAccounts(req.getEntityIds());
    for(Account account : accounts) {
      changeStorageState(client, account, req.getNewStorageState());
    }
    return true;
  }

  public boolean changeStorageState(ClientInfo client, Account account, StorageState state) {
    final String LOGIN_ID = account.getLoginId();
    plugins.forEach(plugin -> {
      plugin.onPreStateChange(client, account, state);
    });

    if(account.getAccountType() == AccountType.USER) {
      userProfileRepo.setStorageState(LOGIN_ID,  state);
    } else {
      orgProfileRepo.setStorageState(LOGIN_ID,  state);
    }
    accountRepo.setStorageState(LOGIN_ID,  state);
    plugins.forEach(plugin -> {
      plugin.onPostStateChange(client, account, state);
    });
    return true;
  }

  public List<Account> searchAccounts(ClientInfo client, SqlQueryParams params) {
    //TODO: manually select field to exclude the password field
    String[] SEARCH_FIELDS = new String[]{"loginId", "email", "fullName", "mobile"};
    SqlQuery query =
        new SqlQuery()
        .ADD_TABLE(new EntityTable(Account.class).selectAllFields())
        .FILTER(
            SearchFilter.isearch(Account.class, SEARCH_FIELDS))
        .FILTER(
            OptionFilter.storageState(Account.class),
            OptionFilter.create(Account.class, "accountType", AccountType.ALL),
            RangeFilter.date(Account.class, "lastLoginTime"),
            RangeFilter.createdTime(Account.class),
            RangeFilter.modifiedTime(Account.class))
        .ORDERBY(new String[] {"loginId", "email", "fullName", "mobile", "modifiedTime"}, "loginId", "DESC");
    return query(client, query, params, Account.class);
  }

  public List<AccountAclModel> findAccountAcls(ClientInfo client, String loginId) {
    SqlQuery query = new SqlQuery()
            .ADD_TABLE(
                    new EntityTable(Account.class)
                            .addSelectField("loginId", "loginId")
                            .addSelectField("priority", "priority"))
            .FILTER(new ClauseFilter(Account.class, "loginId", "=", ":loginId"))
            .FILTER(OptionFilter.storageState(Account.class))
            .ORDERBY(new String[] { "priority" }, "priority", "ASC");
    query.addParam("loginId", loginId);
    return query(client, query, AccountAclModel.class);
  }
}
