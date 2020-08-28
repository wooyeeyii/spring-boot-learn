package com.chang

import com.chang.repository.UserRepository
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.ApplicationContext
import spock.lang.Specification

@WebMvcTest(excludeAutoConfiguration = [ FlywayAutoConfiguration ])
class LoadContextTest extends Specification {

    @SpringBean
    UserRepository userRepository = Mock();

    @Autowired
    ApplicationContext context

    def "test context loads"() {
        given:
        int a = 0;

        expect:
        context != null
        context.containsBean("userController")
        context.containsBean("userRepository")
    }

}
