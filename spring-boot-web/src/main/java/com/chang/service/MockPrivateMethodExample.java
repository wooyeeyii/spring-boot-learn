package com.chang.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Example class to test the mocking of private method.
 *
 * @author Meraj
 */
public class MockPrivateMethodExample {

    @Transactional
    public String getDetails() {
        String r = iAmPrivate();
        System.out.println("output of iAmPrivate: " + r);
        return "Mock private method example: " + r;
    }

    private String iAmPrivate() {
        return new Date().toString();
    }
}
