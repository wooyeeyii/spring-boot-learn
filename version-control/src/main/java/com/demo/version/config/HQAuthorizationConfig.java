package com.demo.version.config;

import com.demo.version.interceptor.HQAuthorizationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class HQAuthorizationConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private HQAuthorizationInterceptor authorizationInterceptor;

    public HQAuthorizationConfig() {
    }

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.authorizationInterceptor);
    }
}
