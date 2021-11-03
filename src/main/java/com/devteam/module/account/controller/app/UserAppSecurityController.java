package com.devteam.module.account.controller.app;

import com.devteam.module.http.rest.RestResponse;
import com.devteam.module.http.rest.v1.BaseController;
import com.devteam.module.security.entity.AppPermission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.concurrent.Callable;

@Api(value="devteam", tags= {"account"})
@RestController
@RequestMapping("/rest/devteam/v1.0.0/security/app")
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