package com.chang.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:${environment.value}/mybatis-config.properties")
@PropertySource("classpath:${environment.value}/flyway-config.properties")
@PropertySource("classpath:${environment.value}/dev.yml")
public class LoadProperties {

    @Value("${environment.value}")
    private String environment;

}
