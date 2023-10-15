package com.anhvu.crm.middleware;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.anhvu.crm.JWT.JwtToken;
import com.anhvu.crm.exception.ExceptionError;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthMiddleware implements HandlerInterceptor {
    @Autowired
    private JwtToken jwtToken;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String authToken = request.getHeader("Authorization");
        if (authToken == null || !jwtToken.validateToken(authToken)) {
            throw new ExceptionError("Invalid JWT token");
        }

        Long userId = jwtToken.getUserIdFromJWT(authToken);
        request.setAttribute("userId", userId);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }
}
