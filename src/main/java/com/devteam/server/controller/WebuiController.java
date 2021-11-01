package com.devteam.server.controller;

import com.devteam.lib.util.dataformat.DataSerializer;
import com.devteam.lib.util.io.IOUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class WebuiController {
    static class Config {
        String build = "latest";
    }

    @GetMapping( value= { "app/config.js" } )
    public void appConfig(HttpServletRequest req, HttpServletResponse response) throws Exception {
        response.setContentType("application/javascript");
        String content = "var CONFIG = " + DataSerializer.JSON.toString(new Config());
        response.getWriter().write(content);
        response.getWriter().flush();
    }

    @GetMapping(
            value= {
                    "/", "/app", "/app/ws:{id}/**", "/login/app"
            }
    )
    public void appPage(HttpServletRequest req, HttpServletResponse response) throws Exception {
        process(req, response);
    }

    void process(HttpServletRequest req, HttpServletResponse response) {
        try {
            response.setContentType("text/html");
            String content = IOUtil.getResourceAsString("public/index.html", "UTF-8");
            response.getWriter().write(content);
            response.getWriter().flush();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}