package com.devteam.digital.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        // When a user tries to access a secure REST resource without providing any credentials, this method will be called to send a 401 response
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException==null?"Unauthorized":authException.getMessage());
    }
}
