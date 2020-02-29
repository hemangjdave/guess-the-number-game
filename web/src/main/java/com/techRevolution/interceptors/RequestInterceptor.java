package com.techRevolution.interceptors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class RequestInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug("PreHandle method called. handler is {}" , handler);
        log.debug("URL :-- {}" , request.getRequestURL());
        log.debug("URI :-- {}" , request.getRequestURI());
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.debug("PostHandle method called. handler is {}" , handler);
        log.debug("URL :-- {}" , request.getRequestURL());
        log.debug("URI :-- {}" , request.getRequestURI());
        log.debug("MODEL :-- {}" , modelAndView.getModel());
        log.debug("VIEW :-- {}" , modelAndView.getViewName());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.debug("afterCompletion method called." );
        log.debug("URL is :-- {}" , request.getRequestURL());
    }
}
