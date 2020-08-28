package com.chang.service;

import org.springframework.stereotype.Service;

@Service
public class TestService {

    public int test() {
        System.out.println("TestService method test");
        return 0;
    }

}
