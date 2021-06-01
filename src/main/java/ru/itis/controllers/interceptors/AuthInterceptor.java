package ru.itis.controllers.interceptors;

import org.springframework.security.core.*;
import org.springframework.security.core.context.*;
import org.springframework.stereotype.*;
import org.springframework.web.servlet.*;

import javax.servlet.http.*;
import java.util.*;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        if (modelAndView == null)
            return;

        populateModel(modelAndView.getModelMap(), request.getRequestURI());
    }

    public void populateModel(Map<String, Object> model, String currentUrl) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var authorities = authentication.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .toArray();

        model.put("authentication", authentication);
        model.put("authorities", authorities);
        model.put("currentUrl", currentUrl);
    }
}
