package com.devteam.server;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

import com.devteam.util.io.FileUtil;

public class ServerLauncher {

  @Test
  public void start() throws Exception {
    String[] args = new String[] {
        "--spring.config.location=src/app/server/config/application.yaml",
        "--spring.profiles.active=h2",
        "--logging.config=src/app/server/config/logback-console.xml",
        "--app.home=./build/app"
    };
    ServerApp.run(args, TimeUnit.MINUTES.toMillis(30));
    Thread.currentThread().join();
  }

  @Test
  public void cleanStart() throws Exception {
    FileUtil.emptyFolder(new File("./build/app"), false);
    start();
  }
}
