package com.onlinekaufen.springframework.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlinekaufen.springframework.dto.Response;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    //handler method
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        String targetUrl = determineRedirectUrl(authentication);
        targetUrl = httpServletRequest.getContextPath() + targetUrl;
        String responseData;
        ObjectMapper mapper = new ObjectMapper();
        if ("true".equals(httpServletRequest.getHeader("X-Ajax-call"))) {
            Response response2 = new Response();
            response2.setStatus(200);
            response2.setMessage(targetUrl);
            responseData = mapper.writeValueAsString(response2);
            httpServletResponse.getWriter().print(responseData);
            httpServletResponse.getWriter().flush();
        }
        handle(httpServletRequest, httpServletResponse, authentication);
        clearAuthenticationAttributes(httpServletRequest);
    }

    //this method clears the session attributes
    private void clearAuthenticationAttributes(HttpServletRequest httpServletRequest) {
        HttpSession sessionAttributes = httpServletRequest.getSession(false);
        if (sessionAttributes == null) {
            return;
        }
        sessionAttributes.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

    // handles the url redirection on successful authentication
    private void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        String redirectUrl = determineRedirectUrl(authentication);
        if (httpServletResponse.isCommitted()) {
            System.out.println("Response is already committed!");
            return;
        }
        redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, redirectUrl);
    }

    //determines the redirect url based on the role of the user
    private String determineRedirectUrl(Authentication auth) {
        String targetUrl = "";
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            System.out.println("Authority is: " + authority);
            switch (authority.getAuthority()) {
                case "ROLE_ADMIN":
                    targetUrl = "/admin/";
                    break;
                case "ROLE_TEACHER":
                    targetUrl = "/teacher";
                    break;
                case "ROLE_STAFF":
                    targetUrl = "/staff";
                    break;
                default:
                    targetUrl = "/factoryOutlet";
                    break;
            }
        }
        return targetUrl;

    }

    public RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

    protected void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
}
