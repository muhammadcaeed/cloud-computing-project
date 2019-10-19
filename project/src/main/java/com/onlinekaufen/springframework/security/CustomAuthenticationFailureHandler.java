package com.onlinekaufen.springframework.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlinekaufen.springframework.dto.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationFailureHandler.class);

    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException auth) throws IOException, ServletException {
        String responseData;

        logger.info("INSIDE AUTHENTICATION FAILURE HANDLER");
        ObjectMapper mapper = new ObjectMapper();
        if ("true".equals(request.getHeader("X-Ajax-call"))) {
            Response response2 = new Response();
            response2.setStatus(400);
            response2.setMessage("Failure");
            responseData = mapper.writeValueAsString(response2);
            response.getWriter().print(responseData);
            response.getWriter().flush();
        } else {
            // this.onAuthenticationFailure(request, response, auth);
            logger.info("FALSE");
        }
    }
}

