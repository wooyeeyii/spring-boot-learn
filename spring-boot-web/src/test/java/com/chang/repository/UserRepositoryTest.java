package com.chang.repository;

import com.chang.config.TitleProperties;
import com.chang.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.text.DateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
@ImportAutoConfiguration(exclude = {FlywayAutoConfiguration.class})
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired(required = false)
    private TitleProperties properties;

    @Test
    public void properties_should_be_null() {
        assertNull(properties);
    }

    @Test
    public void query_delete_test() {
        assertNotNull(userRepository);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        String formattedDate = dateFormat.format(date);

        userRepository.save(new User("aa1", "aa@126.com", "aa", "aa123456", formattedDate, new Date()));
        userRepository.save(new User("bb2", "bb@126.com", "bb", "bb123456", formattedDate, new Date()));
        userRepository.save(new User("cc3", "cc@126.com", "cc", "cc123456", formattedDate, date));

//		Assert.assertEquals(9, userRepository.findAll().size());
        assertEquals("bb123456", userRepository.findByUserNameOrEmail("bb2", "xxx126.com").getNickName());
        userRepository.delete(userRepository.findByUserName("aa1"));

        User u = userRepository.getByCreateTime(date);
        System.out.println(u);
    }

}