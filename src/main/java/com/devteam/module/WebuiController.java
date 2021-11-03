package com.devteam.module;

import com.devteam.module.util.io.IOUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class WebuiController {

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
