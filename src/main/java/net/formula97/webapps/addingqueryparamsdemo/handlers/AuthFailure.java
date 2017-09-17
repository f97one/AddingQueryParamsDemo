package net.formula97.webapps.addingqueryparamsdemo.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
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

        // Analyze the cause of the error
        String errReason = null;
        if (exception instanceof BadCredentialsException) {
            errReason = "Invalid user name or password.";
        } else if (exception instanceof AccountExpiredException) {
            errReason = "This account is expired. Please contact administrator.";
        } else if (exception instanceof CredentialsExpiredException) {
            errReason = "Your password is expired. Please contact administrator.";
        } else if (exception instanceof DisabledException) {
            errReason = "Your password is disabled. Please contact administrator.";
        } else if (exception instanceof LockedException) {
            errReason = "Your accouunt is locked. Please contact administrator.";
        } else {
            errReason = "Unknown problem occured. Please contact administrator.";
        }
        
        if (errReason != null && errReason.length() > 0) {
            HttpSession session = request.getSession();
            session.setAttribute("errReason", errReason);
        }

        RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
        redirectStrategy.sendRedirect(request, response, "/login" + queryParams );
    }

}
