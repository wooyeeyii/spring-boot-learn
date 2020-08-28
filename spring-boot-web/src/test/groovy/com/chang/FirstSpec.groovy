package com.chang

import spock.lang.Specification

class FirstSpec extends Specification {

    def "empty stack pop throw emptyStackException"() {
        given:
        def stack = new Stack()
        assert stack.empty()

        when:
        stack.pop()

        then:
        thrown(EmptyStackException)
        assert stack.empty()

        when:
        stack.pop()

        then:
        def e = thrown(EmptyStackException)
        e.cause == null

        when:
        stack.pop()

        // This syntax is recommended
        then:
        EmptyStackException ex = thrown()
        ex.cause == null
    }

    def "HashMap accepts null key"() {
        given: "init a new hash map"
        def map = new HashMap()

        when: "put a key-value pair which key is null "
        map.put(null, "elem")

        then: "should not throw NPE"
        notThrown(NullPointerException)
    }

}
