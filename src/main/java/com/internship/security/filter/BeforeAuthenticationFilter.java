package com.internship.security.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class BeforeAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final String USERNAME_ERROR_MSG = "Hãy nhập username";
    private final String PASSWORD_ERROR_MSG = "Hãy nhập password";

    public BeforeAuthenticationFilter() {
        super.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
    }

    @Autowired
    @Override
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    @Autowired
    @Override
    public void setAuthenticationSuccessHandler(AuthenticationSuccessHandler successHandler) {
        super.setAuthenticationSuccessHandler(successHandler);
    }

    @Autowired
    @Override
    public void setAuthenticationFailureHandler(AuthenticationFailureHandler failureHandler) {
        super.setAuthenticationFailureHandler(failureHandler);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(username == null) {
            username = "";
        }
        if(password == null) {
            password = "";
        }

        boolean isAnyEmptyField = false;


        //check if username is empty
        if(username.trim().isEmpty()) {
            request.setAttribute("usernameErr", USERNAME_ERROR_MSG);
            isAnyEmptyField = true;
        }

        //keep username
        request.setAttribute("username", username);

        //check if password is empty
        if(password.trim().isEmpty()) {
            request.setAttribute("passwordErr", PASSWORD_ERROR_MSG);
            isAnyEmptyField = true;
        }

        if(isAnyEmptyField) {
            UrlPathHelper helper = new UrlPathHelper();
            String contextPath = helper.getContextPath(request);
            try{
                RequestDispatcher rd = request.getRequestDispatcher(contextPath + "/login");
                rd.forward(request, response);
                return null;
            }catch (IOException | ServletException ex) {
                ex.printStackTrace();
            }
        }
        try {
            return super.attemptAuthentication(request, response);
        } catch (BadCredentialsException badCredentialsException) {
            request.setAttribute("errorMsg", "Mật khẩu không đúng");
        } catch(UsernameNotFoundException usernameNotFoundException) {
            request.setAttribute("errorMsg", "Tên đăng nhập không tồn tại");
        }

        return super.attemptAuthentication(request, response);
    }
}
