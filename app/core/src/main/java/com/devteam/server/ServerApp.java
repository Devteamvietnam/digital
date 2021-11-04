package com.devteam.server;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import com.devteam.util.text.StringUtil;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class ServerApp {
  static ConfigurableApplicationContext context;

  static public ApplicationContext run(String[] args, long wait) throws Exception {
    log.info("Launch ServerApp with args: {}", StringUtil.joinStringArray(args, " "));
    context = SpringApplication.run(ServerAppConfig.class, args);
    isRunning(wait);
    return context;
  }

  static public boolean isRunning(long waitTime) {
    boolean running = false;
    if(waitTime <= 0) waitTime = 1;
    try {
      while(!running && waitTime > 0) {
        if(context == null) running = false;
        else running = context.isRunning();
        waitTime -= 100;
        if(!running && waitTime > 0) Thread.sleep(100);
      }
    } catch(Exception ex) {
      ex.printStackTrace();
    }
    return running;
  }

  static public void exit() {
    if(context != null) {
      SpringApplication.exit(context);
      context = null;
    }
  }

  public static void main(String[] args) throws Exception {
    run(args, 30000);
    Thread.currentThread().join();
  }
}