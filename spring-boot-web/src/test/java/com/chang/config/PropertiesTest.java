package com.chang.config;

import com.chang.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
@ImportAutoConfiguration(exclude = {FlywayAutoConfiguration.class})
public class PropertiesTest {

    @Autowired
    private TitleProperties properties;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void getHello() {
        assertNotNull(userRepository);

        System.out.println(properties.getTitle());
        assertEquals("test", properties.getTitle());
        assertEquals("spring boot test", properties.getDescription());
    }

}