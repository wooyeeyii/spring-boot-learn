package com.chang

import com.chang.config.TitleProperties
import com.chang.model.User
import com.chang.repository.UserRepository
import org.flywaydb.core.Flyway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.annotation.Rollback
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.spock.Testcontainers
import spock.lang.Shared
import spock.lang.Specification

import java.text.SimpleDateFormat

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ImportAutoConfiguration(exclude = [FlywayAutoConfiguration])
class UserRepositoryTestWithLocalMysql extends Specification {

    @Autowired
    private UserRepository userRepository;

    @Autowired(required = false)
    private TitleProperties properties;

    def "properties should be null"() {
        expect:
        null == properties
    }

    @Rollback(false)
    def "jpa native query by date"() {
        given:
        Date date = new Date()
        userRepository.save(User.builder().userName("jun1").password("pass")
                .email("jun1@163.com").regTime("2020-10-10 00:00:00").createTime(date).build())

        expect:
        User u2 = userRepository.findByUserName("jun1")
        u2 != null
        System.out.println(u2)

        User u3 = userRepository.getByCreateTimeStr(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date))
        u3 != null

        User u = userRepository.getByCreateTime(date)
        u != null
    }

}
