package com.devteam.module.http.rest.v1.storage;

import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devteam.module.common.ClientInfo;
import com.devteam.module.http.rest.v1.BaseController;
import com.devteam.module.storage.UserStorage;
import com.devteam.module.storage.fs.FSStorageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "devteam", tags = { "storage" })
@RestController
@RequestMapping("/storage")
public class WWWStorageController extends BaseController {

  @Autowired
  private FSStorageService service;

  protected WWWStorageController() {
    super("storage", "/storage");
  }

  @ApiOperation(value = "Get system www resource", response = ResponseEntity.class)
  @GetMapping("www/{path:.+}")
  public ResponseEntity<Resource> wwwResource(
      HttpServletRequest request,
      @PathVariable("path") String path, @RequestParam boolean download) {

    Resource resource = service.getWWWResource(path);
    String mimeType = request.getServletContext().getMimeType(resource.getFilename());
    return createResource(mimeType, resource, download);
  }

  @ApiOperation(value = "Get user www resource", response = ResponseEntity.class)
  @GetMapping("users/{loginId}/www/**")
  public ResponseEntity<Resource> userWWWResource(
      HttpServletRequest request, @PathVariable("loginId") String loginId, @RequestParam(required = false) boolean download) {
    String path = parsePath(request, "/www/");
    UserStorage storage = service.createUserStorage(ClientInfo.DEFAULT, loginId);
    Resource resource = storage.wwwResource(path);
    String mimeType = request.getServletContext().getMimeType(resource.getFilename());
    return createResource(mimeType, resource, download);
  }

  @ApiOperation(value = "Get user resource", response = ResponseEntity.class)
  @GetMapping("users/private/**")
  public ResponseEntity<Resource> userResource( HttpServletRequest request,  @RequestParam(required = false) boolean download) {
    String path = parsePath(request, "/private/");
    HttpSession session = request.getSession(false);
    ClientInfo client = getAuthorizedClientInfo(session);
    if(client == null) {
      return this.createNotAllowResource("Your are not allowed to access " + path);
    }
    UserStorage storage = service.createUserStorage(ClientInfo.DEFAULT, client.getRemoteUser());
    Resource resource = storage.getContentAsResource(path);
    String mimeType = request.getServletContext().getMimeType(resource.getFilename());
    return createResource(mimeType, resource, download);
  }



  protected String parsePath(HttpServletRequest request, String separator) {
    String requestURL = request.getRequestURL().toString();
    requestURL = java.net.URLDecoder.decode(requestURL, StandardCharsets.UTF_8);
    int idx = requestURL.indexOf(separator);
    if(idx > 0) {
      return requestURL.substring(idx + separator.length());
    }
    return null;
  }
}
