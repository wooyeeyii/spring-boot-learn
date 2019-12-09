package com.chang.config;

import com.chang.service.user.BeanProperties;
import com.chang.service.user.SelfBeanRegistrar;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableConfigurationProperties(BeanProperties.class)
@Import(SelfBeanRegistrar.class)
public class BeanConfig {
}
