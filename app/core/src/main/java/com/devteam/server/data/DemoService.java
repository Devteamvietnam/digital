package com.devteam.server.data;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.devteam.module.common.ClientInfo;
import com.devteam.module.app.AppEnv;


import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DemoService {
  @Autowired
  private AppEnv appEnv;

  @Getter
  @Autowired
  private ApplicationContext context;

  protected void runImport(ApplicationContext context, ClientInfo client, String module, String plugin) throws Exception {
    log.info("Import the demo data for module {}, pluging {}", module, plugin);
    String importFile = "download/sample/app/" + module + "/" + plugin + ".xlsx" ;
    importFile = appEnv.filePath(importFile);
    File file = new File(importFile);
    if(!file.exists()) {
      log.info("Cannot import file {}. File does not exist", importFile);
      return;
    }
  }

}
