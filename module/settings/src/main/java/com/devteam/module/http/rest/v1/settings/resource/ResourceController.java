package com.devteam.module.http.rest.v1.settings.resource;

import java.util.List;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.devteam.module.data.db.entity.ChangeStorageStateRequest;
import com.devteam.module.data.db.query.SqlQueryParams;
import com.devteam.module.http.rest.RestResponse;
import com.devteam.module.http.rest.v1.BaseController;
import com.devteam.module.settings.resource.service.ResourceService;
import com.devteam.module.settings.resource.entity.ResourceEntity;
import com.devteam.module.settings.resource.entity.ResourceType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="devteam", tags= {"settings/resource"})
@RestController
@RequestMapping("/rest/dev/v1.0.0/settings/resource")
public class ResourceController extends BaseController {
  @Autowired
  private ResourceService service;

  protected ResourceController() {
    super("resource", "/resource");
  }

  @ApiOperation(value = "Search Resource Type", responseContainer = "List", response = ResourceType.class)
  @PostMapping("type/search")
  public @ResponseBody RestResponse searchResourceType(HttpSession session, @RequestBody SqlQueryParams params) {
    Callable<List<ResourceType>> executor = () -> {
      return service.searchResourceType(getAuthorizedClientInfo(session), params);
    };
    return execute(Method.POST, "search-resource-type", executor);
  }

  @ApiOperation(value = "Load the resource type", response = ResourceType.class)
  @GetMapping("type/{type}")
  public @ResponseBody RestResponse loadResourceType( HttpSession session, @PathVariable("type") String type){
    Callable<ResourceType> executor = () -> {
      return service.getResourceTypeByType(getAuthorizedClientInfo(session), type);
    };
    return execute(Method.GET, "type/{type}", executor);
  }

  @ApiOperation(value = "Save the resource type", response = ResourceType.class)
  @PutMapping("type")
  public @ResponseBody RestResponse saveResourceType(HttpSession session, @RequestBody ResourceType resourceType) {
    Callable<ResourceType> executor = () -> {
      return service.saveResourceType(getAuthorizedClientInfo(session), resourceType);
    };
    return execute(Method.PUT, "save-resource-type", executor);
  }

  @ApiOperation(value = "Change the storage state", response = Boolean.class)
  @PutMapping("type/storage-state")
  public @ResponseBody RestResponse changeResourceTypeStorageState(HttpSession session, @RequestBody ChangeStorageStateRequest req) {
    Callable<Boolean> executor = () -> {
      return service.changeResourceTypeStorageState(getAuthorizedClientInfo(session), req);
    };
    return execute(Method.PUT, "type/storage-state", executor);
  }

  @ApiOperation(value = "Search Resource EntityId", responseContainer = "List", response = ResourceEntity.class)
  @PostMapping("entity/search")
  public @ResponseBody RestResponse searchResourceEntity(HttpSession session, @RequestBody SqlQueryParams params) {
    Callable<List<ResourceEntity>> executor = () -> {
      return service.searchResourceEntityByType(getAuthorizedClientInfo(session), params);
    };
    return execute(Method.POST, "search-resource-entity", executor);
  }

  @ApiOperation(value = "Load the resource entity", response = ResourceEntity.class)
  @GetMapping("entity/{code}/{resourceType}")
  public @ResponseBody RestResponse loadResourceEntity( HttpSession session,
      @PathVariable("code") String code, @PathVariable("resourceType") String resourceType) {
    Callable<ResourceEntity> executor = () -> {
      return service.getResourceEntityByCodeAndResourceType(getAuthorizedClientInfo(session), code, resourceType);
    };
    return execute(Method.GET, "entity/{code}", executor);
  }

  @ApiOperation(value = "Save the resource entity", response = ResourceEntity.class)
  @PutMapping("entity")
  public @ResponseBody RestResponse saveResourceEntity(HttpSession session, @RequestBody ResourceEntity resourceEntity) {
    Callable<ResourceEntity> executor = () -> {
      return service.saveResourceEntity(getAuthorizedClientInfo(session), resourceEntity);
    };
    return execute(Method.PUT, "save-resource-entity", executor);
  }

  @ApiOperation(value = "Delete the resource entity", response = Boolean.class)
  @DeleteMapping("entity")
  public @ResponseBody RestResponse deleteResourceEntity(HttpSession session, @RequestBody() Long ... id) {
    Callable<Boolean> executor = () -> {
      return service.deleteResourceEntity(getAuthorizedClientInfo(session), id);
    };
    return execute(Method.DELETE, "resource-entity/{id}", executor);
  }
}