package com.shoponeo.configuration.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Tomasz on 15.12.2016.
 */
@Component
public class RestUnauthorizedEntryPoint implements AuthenticationEntryPoint {

    /**
     * Always returns a 401 error code to the client.
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException exception) throws IOException, ServletException {
        SecurityUtils.sendError(response, exception, HttpServletResponse.SC_UNAUTHORIZED,
                "Authentication failed");
    }
}
