package com.devteam.module;

import com.devteam.module.util.filter.FilterConfig;
import com.devteam.module.web.WebResourceConfig;
import com.devteam.module.web.WebSecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableConfigurationProperties
@SpringBootApplication(
        exclude = {
                SecurityAutoConfiguration.class
        }
)
@Import(value = {
        WebSecurityConfig.class, WebResourceConfig.class, FilterConfig.class,
})
public class DigitalApplication {

    static ConfigurableApplicationContext context;

    static public ApplicationContext run(String[] args, long wait) throws Exception {
        System.out.println(" DEVTEAM SERVER START  \n");
        context = SpringApplication.run(DigitalApplication.class, args);
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
