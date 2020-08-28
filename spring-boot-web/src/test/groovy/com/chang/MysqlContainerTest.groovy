package com.chang

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.flywaydb.core.Flyway
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.spock.Testcontainers
import spock.lang.Shared;
import spock.lang.Specification;

import java.sql.ResultSet;
import java.sql.Statement;

@Testcontainers
class MysqlContainerTest extends Specification {

    @Shared
    MySQLContainer database = new MySQLContainer<>()
            .withDatabaseName("test")

    def setupSpec() {
        Flyway.configure().dataSource(database.jdbcUrl, database.username, database.password).load().migrate()
    }

    def "waits until postgres accepts jdbc connections"() {
        given: "a jdbc connection"
        HikariConfig hikariConfig = new HikariConfig()
        hikariConfig.setJdbcUrl(database.jdbcUrl)
        hikariConfig.setUsername(database.username)
        hikariConfig.setPassword(database.password)
        HikariDataSource ds = new HikariDataSource(hikariConfig)

        when: "querying the database"
        Statement statement = ds.getConnection().createStatement()
        statement.execute("SELECT * from user where user_name = 'jun'")
        ResultSet resultSet = statement.getResultSet()
        resultSet.next()

        then: "result is returned"
        verifyAll {
            resultSet.getString("user_name") == 'jun'
            resultSet.getString("email") == 'jun@163.com'
        }

        cleanup:
        ds.close()
    }

}
