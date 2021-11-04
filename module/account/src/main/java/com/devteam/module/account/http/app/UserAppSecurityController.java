package com.devteam.module.account.http.app;

import java.util.concurrent.Callable;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.devteam.module.security.entity.AppPermission;
import com.devteam.module.http.rest.RestResponse;
import com.devteam.module.http.rest.v1.BaseController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="devteam", tags= {"account"})
@RestController
@RequestMapping("/rest/dev/v1.0.0/security/app")
public class UserAppSecurityController extends BaseController {

  protected UserAppSecurityController() {
    super("security", "/security/app");
  }

  @ApiOperation(value = "Change the storage state", response = AppPermission.class)
  @PutMapping("permission")
  public @ResponseBody RestResponse addUserPermission(HttpSession session, @RequestBody AppPermission permission) {
    Callable<Boolean> executor = () -> {
      return null;
    };
    return execute(Method.PUT, "permission", executor);
  }
}