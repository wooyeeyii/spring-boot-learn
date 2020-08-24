package com.chang.repository;

import com.chang.model.User;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.text.DateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Container
    private static final MySQLContainer database = new MySQLContainer().withDatabaseName("test");

    @DynamicPropertySource
    static void setMysqlProperty(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", database::getJdbcUrl);
        registry.add("spring.datasource.username", database::getUsername);
        registry.add("spring.datasource.password", database::getPassword);
    }

    @Autowired
    private UserRepository userRepository;

    @BeforeAll
    static void init() {
        Flyway.configure().dataSource(database.getJdbcUrl(), database.getUsername(), database.getPassword()).load().migrate();
    }

    @Test
    public void test() {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        String formattedDate = dateFormat.format(date);

        userRepository.save(new User("aa1", "aa@126.com", "aa", "aa123456", formattedDate));
        userRepository.save(new User("bb2", "bb@126.com", "bb", "bb123456", formattedDate));
        userRepository.save(new User("cc3", "cc@126.com", "cc", "cc123456", formattedDate));

//		Assert.assertEquals(9, userRepository.findAll().size());
        assertEquals("bb123456", userRepository.findByUserNameOrEmail("bb2", "xxx126.com").getNickName());
        userRepository.delete(userRepository.findByUserName("aa"));
    }

}