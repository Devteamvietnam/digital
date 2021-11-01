package com.devteam;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties
@SpringBootApplication(
        exclude = {
                SecurityAutoConfiguration.class
        }
)
@Configuration
public class ServerApp {
    static ConfigurableApplicationContext context;

    static public ApplicationContext run(String[] args, long wait) throws Exception {
        System.out.println("(♥◠‿◠)ﾉﾞ  Stating Project Success ლ(´ڡ`ლ)ﾞ  \n");
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