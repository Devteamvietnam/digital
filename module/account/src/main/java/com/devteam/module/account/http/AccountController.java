package com.devteam.module.account.http;

import java.util.List;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.devteam.module.account.service.AccountService;
import com.devteam.module.account.model.ChangePasswordRequest;
import com.devteam.module.account.model.NewAccountModel;
import com.devteam.module.account.model.ResetPasswordRequest;
import com.devteam.module.account.entity.Account;
import com.devteam.module.account.entity.AccountGroup;
import com.devteam.module.common.Result;
import com.devteam.module.data.db.entity.ChangeStorageStateRequest;
import com.devteam.module.data.db.query.SqlQueryParams;
import com.devteam.module.http.rest.RestResponse;
import com.devteam.module.http.rest.v1.AuthenticationService;
import com.devteam.module.http.rest.v1.BaseController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="devteam", tags= {"account"})
@RestController
@ConditionalOnBean(AuthenticationService.class)
@RequestMapping("/rest/dev/v1.0.0/account")
public class AccountController extends BaseController {
  @Autowired
  private AccountService service;

  protected AccountController() {
    super("account", "/account");
  }


  @ApiOperation(value = "Create the account group", response = AccountGroup.class)
  @PutMapping("group/{parentId}/create")
  public @ResponseBody RestResponse createAccountGroup(HttpSession session, @PathVariable("parentId") Long parentId, @RequestBody AccountGroup group) {
    Callable<AccountGroup> executor = () -> {
      return service.createAccountGroup(getAuthorizedClientInfo(session), parentId, group);
    };
    return execute(Method.PUT, "group/{parentId}/save", executor);
  }

  @ApiOperation(value = "Save the account group", response = AccountGroup.class)
  @PutMapping("group")
  public @ResponseBody RestResponse updateAccountGroup(HttpSession session, @RequestBody AccountGroup group) {
    Callable<AccountGroup> executor = () -> {
      return service.updateAccountGroup(getAuthorizedClientInfo(session), group);
    };
    return execute(Method.PUT, "group", executor);
  }

  @ApiOperation(value = "Find the children account group", responseContainer="List", response = AccountGroup.class)
  @GetMapping("group/children")
  public @ResponseBody RestResponse findAccountGroupChildren(HttpSession session, @RequestParam String parentPath) {
    Callable<List<AccountGroup>> executor = () -> {
      return service.findAccountGroupChildren(getAuthorizedClientInfo(session), parentPath);
    };
    return execute(Method.GET, "group/children", executor);
  }

  @ApiOperation(value = "Delete the account group", response = Boolean.class)
  @DeleteMapping("group/{id}")
  public @ResponseBody RestResponse deleteAccountGroup(HttpSession session, @PathVariable("id") Long id) {
    Callable<Boolean> executor = () -> {
      return service.deleteAccountGroup(getAuthorizedClientInfo(session), id);
    };
    return execute(Method.DELETE, "group/{id}", executor);
  }

  @ApiOperation(value = "Change Password the account", response = Boolean.class)
  @PutMapping("change-password")
  public @ResponseBody RestResponse changePassword(HttpSession session, @RequestBody ChangePasswordRequest request) {
    Callable<Result<Boolean>> executor = () -> {
      return service.changePassword(getAuthorizedClientInfo(session), request);
    };
    return execute(Method.PUT, "change-password", executor);
  }

  @ApiOperation(value = "Reset Password the account", response = Boolean.class)
  @PutMapping("reset-password")
  public @ResponseBody RestResponse resetPassword(HttpSession session, @RequestBody ResetPasswordRequest request) {
    Callable<Result<Boolean>> executor = () -> {
      return service.resetPassword(getAuthorizedClientInfo(session), request);
    };
    return execute(Method.PUT, "reset-password", executor);
  }

  @ApiOperation(value = "Save the account memberships", response = Boolean.class)
  @PutMapping("group/{groupId}/memberships")
  public @ResponseBody RestResponse createAccountMemberships(
      HttpSession session, @PathVariable("groupId") Long groupId, @RequestBody List<String> accountLoginIds) {
    Callable<Boolean> executor = () -> {
      service.createAccuntMemberships(getAuthorizedClientInfo(session), groupId, accountLoginIds);
      return true;
    };
    return execute(Method.PUT, "group/{groupId}/memberships", executor);
  }

  @ApiOperation(value = "Delete the account memberships", response = Boolean.class)
  @DeleteMapping("group/{groupId}/memberships")
  public @ResponseBody RestResponse deleteAccountMemberships(
      HttpSession session, @PathVariable("groupId") Long groupId, @RequestBody List<String> accountLoginIds) {
    Callable<Boolean> executor = () -> {
      service.deleteAccountMemberships(getAuthorizedClientInfo(session), groupId, accountLoginIds);
      return true;
    };
    return execute(Method.DELETE, "group/{groupId}/memberships", executor);
  }

  @ApiOperation(value = "Persist the account model", response = NewAccountModel.class)
  @PutMapping("account/create")
  public @ResponseBody RestResponse createAccount(HttpSession session, @RequestBody NewAccountModel model) {
    Callable<NewAccountModel> executor = () -> {
      return service.createNewAccount(getAuthorizedClientInfo(session), model);
    };
    return execute(Method.PUT, "account", executor);
  }

  @ApiOperation(value = "Persist the account", response = Account.class)
  @PutMapping("account")
  public @ResponseBody RestResponse saveAccount(HttpSession session, @RequestBody Account account) {
    Callable<Account> executor = () -> {
      return service.saveAccount(getAuthorizedClientInfo(session), account);
    };
    return execute(Method.PUT, "account", executor);
  }

  @ApiOperation(value = "Change the storage state", response = Boolean.class)
  @PutMapping("account/storage-state")
  public @ResponseBody RestResponse changeStorageState(HttpSession session, @RequestBody ChangeStorageStateRequest req) {
    Callable<Boolean> executor = () -> {
      return service.changeAccountStorageState(getAuthorizedClientInfo(session), req);
    };
    return execute(Method.PUT, "account/storage-state", executor);
  }

  @ApiOperation(value = "Search Accounts", responseContainer = "List", response = Account.class)
  @PostMapping("account/search")
  public @ResponseBody RestResponse  searchAccounts(
      HttpSession session, @RequestBody SqlQueryParams params) {
    Callable<List<Account>> executor = () -> {
      return service.searchAccounts(getAuthorizedClientInfo(session), params);
    };
    return execute(Method.POST, "account/search", executor);
  }
}