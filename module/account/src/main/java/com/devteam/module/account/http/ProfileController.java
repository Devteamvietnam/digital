package com.devteam.module.account.http;

import java.util.List;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.devteam.module.account.service.AccountService;
import com.devteam.module.account.entity.AccountContact;
import com.devteam.module.account.entity.BaseProfile;
import com.devteam.module.account.entity.OrgProfile;
import com.devteam.module.account.entity.UserProfile;
import com.devteam.module.common.ClientInfo;
import com.devteam.module.http.rest.RestResponse;
import com.devteam.module.http.rest.v1.AuthenticationService;
import com.devteam.module.http.rest.v1.BaseController;
import com.devteam.module.http.upload.UploadResource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="devteam", tags= {"account"})
@RestController
@ConditionalOnBean(AuthenticationService.class)
@RequestMapping("/rest/dev/v1.0.0/account")
public class ProfileController extends BaseController {
  @Autowired
  private AccountService service;

  protected ProfileController() {
    super("account", "/account");
  }

  @ApiOperation(value = "Retrieve Account profile", response = BaseProfile.class)
  @GetMapping("profile/{loginId}")
  public @ResponseBody <T extends BaseProfile> RestResponse getProfile(HttpSession session, @PathVariable("loginId") String loginId) {
    Callable<T> executor = () -> {
      ClientInfo clientInfo = getAuthorizedClientInfo(session);
      return service.getProfile(clientInfo, loginId);
    };
    return execute(Method.GET, "profile/{loginId}", executor);
  }

  @ApiOperation(value = "Save the user profile", response = UserProfile.class)
  @PutMapping("profile/user")
  public @ResponseBody RestResponse saveUserProfile(HttpSession session, @RequestBody UserProfile profile) {
    Callable<UserProfile> executor = () -> {
      ClientInfo clientInfo = getAuthorizedClientInfo(session);
      return service.saveUserProfile(clientInfo, profile);
    };
    return execute(Method.PUT, "profile/user", executor);
  }

  @ApiOperation(value = "Save the user profile", response = UserProfile.class)
  @PutMapping("profile/org")
  public @ResponseBody RestResponse saveOrgProfile(HttpSession session, @RequestBody OrgProfile profile) {
    Callable<OrgProfile> executor = () -> {
      ClientInfo clientInfo = getAuthorizedClientInfo(session);
      return service.saveOrgProfile(clientInfo, profile);
    };
    return execute(Method.PUT, "profile/user", executor);
  }

  @ApiOperation(value = "Upload user avatar", response = UploadResource.class)
  @PutMapping("profile/{loginId}/upload-avatar")
  public @ResponseBody RestResponse uploadAvatar(
      HttpSession session, @PathVariable("loginId") String loginId, @RequestBody UploadResource resource) {
    Callable<UploadResource> executor = () -> {
      ClientInfo clientInfo = getAuthorizedClientInfo(session);
      return service.uploadAvatar(clientInfo, loginId, resource, true);
    };
    return execute(Method.PUT, "profile/user", executor);
  }

  @ApiOperation(value = "Upload user avatar", response = UploadResource.class)
  @PutMapping("profile/{loginId}/modify-avatar")
  public @ResponseBody RestResponse modifyAvatar(
      HttpSession session, @PathVariable("loginId") String loginId, @RequestBody UploadResource resource) {
    Callable<UploadResource> executor = () -> {
      ClientInfo clientInfo = getAuthorizedClientInfo(session);
      return service.uploadAvatar(clientInfo, loginId, resource, false);
    };
    return execute(Method.PUT, "profile/user", executor);
  }

  @ApiOperation(value = "Retrieve the account contacts by loginId", responseContainer = "List", response = AccountContact.class)
  @GetMapping("contact/{loginId}/find")
  public @ResponseBody RestResponse findContactByLoginId(HttpSession session, @PathVariable("loginId") String loginId) {
    Callable<List<AccountContact>> executor = () -> {
      ClientInfo clientInfo = getAuthorizedClientInfo(session);
      return service.findAccountContact(clientInfo, loginId);
    };
    return execute(Method.GET, "contact/{loginId}/find", executor);
  }

  @ApiOperation(value = "Save account contact", response = AccountContact.class)
  @PutMapping("contact/{loginId}/save")
  public @ResponseBody RestResponse saveContacts(
      HttpSession session, @PathVariable("loginId") String loginId, @RequestBody AccountContact contact) {
    Callable<AccountContact> executor = () -> {
      ClientInfo clientInfo = getAuthorizedClientInfo(session);
      return service.saveAccountContact(clientInfo, loginId, contact);
    };
    return execute(Method.PUT, "contact/{loginId}/save", executor);
  }

  @ApiOperation(value = "Delete the account memberships", response = Boolean.class)
  @DeleteMapping("contact/{loginId}")
  public @ResponseBody RestResponse deleteContacts(
      HttpSession session, @PathVariable("loginId") String loginId, @RequestBody List<Long> contactId) {
    Callable<Boolean> executor = () -> {
      service.deleteAccountContacts(getAuthorizedClientInfo(session), loginId, contactId);
      return true;
    };
    return execute(Method.DELETE, "contact/{loginId}", executor);
  }
}