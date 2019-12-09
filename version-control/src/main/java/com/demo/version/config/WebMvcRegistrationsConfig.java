package com.demo.version.config;

import com.demo.version.anno.ApiVersionRequestMappingHandlerMapping;
import com.demo.version.interceptor.HQAuthorizationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
public class WebMvcRegistrationsConfig extends WebMvcConfigurationSupport {

    @Autowired
    private HQAuthorizationInterceptor authorizationInterceptor;

    @Override
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        RequestMappingHandlerMapping handlerMapping = new ApiVersionRequestMappingHandlerMapping();
        handlerMapping.setOrder(50);
        handlerMapping.setInterceptors(getInterceptors());
        return handlerMapping;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.authorizationInterceptor);
    }

}
