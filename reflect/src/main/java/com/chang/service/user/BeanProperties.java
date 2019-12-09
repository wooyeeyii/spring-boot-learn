package com.chang.service.user;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@Data
@ConfigurationProperties(prefix = "self.bean.props")
public class BeanProperties {

    private Map<String, User> users;

    @Data
    public static class User {
        private String name;
        private String sex;
    }

}
