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
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.spock.Testcontainers
import spock.lang.Shared
import spock.lang.Specification

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ImportAutoConfiguration(exclude = [FlywayAutoConfiguration])
class UserRepositoryTest extends Specification {

    @Shared
    MySQLContainer database = new MySQLContainer<>().withDatabaseName("test")

    def setupSpec() {
        System.setProperty("spring.datasource.url", database.jdbcUrl)
        System.setProperty("spring.datasource.username", database.username)
        System.setProperty("spring.datasource.password", database.password)
        Flyway.configure().dataSource(database.jdbcUrl, database.username, database.password).load().migrate()
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired(required = false)
    private TitleProperties properties;

    def "properties should be null"() {
        expect:
        null == properties
    }

    def "insert user should be success"() {
        given:
        userRepository.save(User.builder().userName("jun").password("pass")
                .email("jun@163.com").regTime("2020-10-10 00:00:00").build())
        userRepository.save(User.builder().userName("jie").password("pass")
                .email("jie@163.com").regTime("2020-10-10 00:00:00").build())

        expect:
        userRepository.findByUserName(a).getUserName() == c

        where:
        a     | c
        "jun" | "jun"
        "jie" | "jie"
    }

}
