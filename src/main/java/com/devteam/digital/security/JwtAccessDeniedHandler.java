package com.devteam.digital.security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        //When a user accesses a protected REST resource without authorization, this method will be called to send a 403 Forbidden response
        response.sendError(HttpServletResponse.SC_FORBIDDEN, accessDeniedException.getMessage());
    }
}
