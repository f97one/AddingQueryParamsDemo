package net.formula97.webapps.addingqueryparamsdemo.handlers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.logout.LogoutHandler;

public class LogoutPostProcess implements LogoutHandler {

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        try {
            // add whole query parameters to url 
            String queryParams = request.getQueryString() == null ? "" : "?" + request.getQueryString();

            RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
            redirectStrategy.sendRedirect(request, response, "/login" + queryParams );
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

}
