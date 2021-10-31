package com.devteam.module.account.http;


import com.devteam.common.ClientInfo;
import com.devteam.core.http.rest.RestResponse;
import com.devteam.core.http.rest.v1.AuthenticationService;
import com.devteam.core.http.rest.v1.BaseController;
import com.devteam.module.account.entity.UserIdentity;
import com.devteam.module.account.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.concurrent.Callable;

@Api(value="vion", tags= {"account"})
@RestController
@ConditionalOnBean(AuthenticationService.class)
@RequestMapping("/rest/devteam/v1.0.0/account")
public class UserController extends BaseController {
  @Autowired
  private AccountService service;

  protected UserController() {
    super("account", "/account");
  }


// User Identity
  @ApiOperation(value = "Retrieve the user-identities by loginId", responseContainer = "List")
  @GetMapping("user-identity/{loginId}/find")
  public @ResponseBody RestResponse findUserIdentity(HttpSession session, @PathVariable("loginId") String loginId) {
    Callable<List<UserIdentity>> executor = () -> {
      ClientInfo clientInfo = getAuthorizedClientInfo(session);
      return service.findUserIdentity(clientInfo, loginId);
    };
    return  execute(Method.GET, "user-identity/{loginId}/find", executor);
  }

  @ApiOperation(value = "Save user Identiity", response = UserIdentity.class)
  @PutMapping("user-identity/{loginId}/save")
  public @ResponseBody RestResponse saveUserIdentity(HttpSession session, @PathVariable("loginId") String loginId,
      @RequestBody UserIdentity identity) {
    Callable<UserIdentity> executor = () -> {
      ClientInfo client = getAuthorizedClientInfo(session);
      return service.saveUserIdentity(client, loginId, identity);
    };
    return execute(Method.PUT, "user-identity/{loginId}/save", executor);
  }

  @ApiOperation(value = "Delete the user identity", response = Boolean.class)
  @DeleteMapping("user-identity/{loginId}")
  public @ResponseBody RestResponse deleteUserIdentity(HttpSession session, @PathVariable("loginId") String loginId,
      @RequestBody List<Long> identityIds) {
    Callable<Boolean> executor = () -> {
      ClientInfo client = getAuthorizedClientInfo(session);
      service.deleteUserIdentity(client, loginId, identityIds);
      return true;
    };
    return execute(Method.DELETE, "user-identity/{loginId}", executor);
  }

}