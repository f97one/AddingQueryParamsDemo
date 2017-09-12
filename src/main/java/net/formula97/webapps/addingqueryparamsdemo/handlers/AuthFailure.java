package net.formula97.webapps.addingqueryparamsdemo.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class AuthFailure implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        
        // add whole query parameters to url 
        String queryParams = request.getQueryString() == null ? "" : "?" + request.getQueryString();
        
        // add error parameter if not present
        if (!queryParams.contains("error")) {
            if (queryParams.length() == 0) {
                queryParams = queryParams + "?error";
            } else {
                queryParams = queryParams + "&error";
            }
        }

        RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
        redirectStrategy.sendRedirect(request, response, "/login" + queryParams );
    }

}
