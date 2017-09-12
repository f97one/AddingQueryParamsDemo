package net.formula97.webapps.addingqueryparamsdemo.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class AuthSuccess implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        
        // add whole query parameters to url 
        String queryParams = request.getQueryString() == null ? "" : "?" + request.getQueryString();
        
        // remove error parameter if present.
        if (queryParams.contains("error&")) {
            queryParams.replaceAll("error&", "");
        } else if (queryParams.contains("&error")) {
            queryParams.replaceAll("&error", "");
        }

        RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
        redirectStrategy.sendRedirect(request, response, "/menu" + queryParams );
    }

}
