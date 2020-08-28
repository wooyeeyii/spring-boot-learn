package com.chang

import com.chang.service.TestService
import spock.lang.Shared
import spock.lang.Specification

class MockServiceSpec extends Specification {

    @Shared
    def serviceMock = Mock(TestService)

    def setupSpec() {
        serviceMock.test() >>> [1, 2, 3]
    }

    def "mock testService test method three times, should print different"() {
        expect:
        serviceMock.test() == r

        where:
        _ | r
        _ | 1
        _ | 2
        _ | 3
    }

}
