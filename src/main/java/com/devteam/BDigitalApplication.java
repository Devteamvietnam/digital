package com.devteam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableConfigurationProperties
@SpringBootApplication(
        exclude = {
                SecurityAutoConfiguration.class
        }
)
public class BDigitalApplication {

    static ConfigurableApplicationContext context;

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

    public static void main(String[] args) {
        SpringApplication.run(BDigitalApplication.class, args);
        isRunning(3000);
    }

}
