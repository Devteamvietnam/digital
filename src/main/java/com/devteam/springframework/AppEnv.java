package com.devteam.springframework;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class AppEnv {
  @Getter
  @Value("${app.home:build/app}")
  private String appHome;

  @Getter
  @Value("${app.config.dir:#{null}}")
  private String appConfigDir;

  @Getter
  @Value("${app.upload.dir:#{null}}")
  private String uploadDir;

  @Getter
  @Value("${app.storage.dir:#{null}}")
  private String storageDir ;

  @PostConstruct
  public void onInit() throws Exception {
    if(appConfigDir == null) {
      appConfigDir = appHome + "/config" ;
    }
    if(uploadDir == null) {
      uploadDir = appHome + "/upload" ;
    }

    if(storageDir == null) {
      storageDir = appHome + "/data/storage" ;
    }
  }


  public String fileResourcePath(String relativePath) {
    if(appHome.startsWith("/")) {
      return "file:" + appHome + "/" + relativePath;
    }
    return "file:/" + appHome + "/" + relativePath;
  }

  public String filePath(String relativePath) {
    return appHome + "/" + relativePath;
  }
}
