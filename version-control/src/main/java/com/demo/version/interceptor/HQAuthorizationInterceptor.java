package com.demo.version.interceptor;

import com.demo.version.anno.HQAuthorization;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class HQAuthorizationInterceptor extends HandlerInterceptorAdapter {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("enter HQAuthorizationInterceptor..");
        if (handler instanceof HandlerMethod) {
            request.setAttribute("REQ_START_TIME", System.currentTimeMillis());
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            HQAuthorization annotation = (HQAuthorization) method.getAnnotation(HQAuthorization.class);
            if (annotation != null) {
                System.out.println("request include HQAuthorization");
            }

        }
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        request.setAttribute("REQ_RUN_CONTROLLER", true);
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Long startTime = (Long) request.getAttribute("REQ_START_TIME");
        if (startTime != null) {
            System.out.println("request spend " + (System.currentTimeMillis() - startTime) + " millis");
        }

    }

}
