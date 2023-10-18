package com.anhvu.crm.middleware;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.anhvu.crm.JWT.JwtToken;
import com.anhvu.crm.entity.AuthToken;
import com.anhvu.crm.exception.ExceptionError;
import com.anhvu.crm.interfaces.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthMiddleware implements HandlerInterceptor {
    @Autowired
    private JwtToken jwtToken;
    private AuthService authService;

    @Autowired
    public AuthMiddleware(AuthService theAuthService) {
        authService = theAuthService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String token = request.getHeader("Authorization");
        if (token == null) {
            throw new ExceptionError("Invalid JWT token");
        }
        AuthToken authToken = authService.findByKeyToken(token);
        if (authToken == null) {
            throw new ExceptionError("Invalid JWT token");
        }
        if (jwtToken.validateToken(token, authToken.getPublicKey(), authToken.getId()) == false) {
            throw new ExceptionError("Invalid JWT token");
        }

        Long userId = jwtToken.getUserIdFromJWT(token, authToken.getPublicKey());
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
