package ru.itis.controllers.interceptors;

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

    }
}
