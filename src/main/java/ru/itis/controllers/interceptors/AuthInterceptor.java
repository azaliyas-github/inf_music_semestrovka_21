package ru.itis.controllers.interceptors;

import org.springframework.security.core.*;
import org.springframework.security.core.context.*;
import org.springframework.stereotype.*;
import org.springframework.web.servlet.*;
import ru.itis.dto.*;

import javax.servlet.http.*;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        if (modelAndView == null)
            return;

        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var authorities = authentication.getAuthorities().stream()
	        .map(GrantedAuthority::getAuthority)
	        .toArray();
        modelAndView.addObject("authentication", authentication);
        modelAndView.addObject("authorities", authorities);
    }
}
