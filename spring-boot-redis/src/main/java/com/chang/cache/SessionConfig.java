package com.chang.cache;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 86400 * 30)
public class SessionConfig {
    // maxInactiveIntervalInSeconds: 设置Session失效时间，
    // 使用Redis Session之后，原Spring Boot的server.session.timeout属性不再生效。
}

