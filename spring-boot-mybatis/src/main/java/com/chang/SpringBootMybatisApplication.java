package com.chang;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;

@Slf4j
@SpringBootApplication
@MapperScan("com.chang.mapper")
@EnableConfigurationProperties
public class SpringBootMybatisApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(SpringBootMybatisApplication.class, args);
        log.info(ctx.getEnvironment().getProperty("test.content"));
    }


}
