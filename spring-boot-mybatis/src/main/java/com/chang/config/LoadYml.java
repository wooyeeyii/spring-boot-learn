package com.chang.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

// 与@PropertySource不能同用
//@Configuration
public class LoadYml {

    @Bean
    public static PropertySourcesPlaceholderConfigurer loadProperties() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        //File路径引入
        yaml.setResources(new FileSystemResource("/dev/dev.yml"));  //文件找不到？
        //class路径引入
        //yaml.setResources(new ClassPathResource(environment + "/dev.yml"));
        configurer.setProperties(yaml.getObject());
        return configurer;
    }

}
