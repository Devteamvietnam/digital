package com.devteam.module.security.controller;

import com.devteam.module.http.rest.RestResponse;
import com.devteam.module.http.rest.v1.BaseController;
import com.devteam.module.security.SecurityService;
import com.devteam.module.security.entity.App;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.concurrent.Callable;

@Api(value="devteam", tags= {"security"})
@RestController
@RequestMapping("/rest/devteam/v1.0.0/security")
public class AppSecurityController extends BaseController {
  @Autowired
  private SecurityService service;

  protected AppSecurityController() {
    super("security", "/security");
  }

  @ApiOperation(value = "Get app by name", response = App.class)
  @GetMapping("app/{module}/{appName}")
  public @ResponseBody RestResponse getApp(
      HttpSession session, @PathVariable("module") String module, @PathVariable("appName") String appName) {
    Callable<App> executor = () -> {
      return service.getApp(getAuthorizedClientInfo(session), module, appName);
    };
    return execute(Method.GET, "app", executor);
  }

  @ApiOperation(value = "Save App", response = App.class)
  @PutMapping("app")
  public @ResponseBody RestResponse saveApp(HttpSession session, @RequestBody App app) {
    Callable<App> executor = () -> {
      return service.saveApp(getAuthorizedClientInfo(session), app);
    };
    return execute(Method.PUT, "app", executor);
  }

  @ApiOperation(value = "Find all app", responseContainer="List", response = App.class)
  @GetMapping("app/all")
  public @ResponseBody RestResponse getAllApps(HttpSession session) {
    Callable<List<App>> executor = () -> {
      return service.findApps(getAuthorizedClientInfo(session));
    };
    return execute(Method.GET, "app/all", executor);
  }
}