package com.demo.version.config;

import com.demo.version.interceptor.HQAuthorizationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class HQAuthorizationConfig implements WebMvcConfigurer {

    @Autowired
    private HQAuthorizationInterceptor authorizationInterceptor;

    public HQAuthorizationConfig() {
    }

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.authorizationInterceptor);
    }

}
