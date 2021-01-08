package com.chang.service;

import com.chang.model.User;
import com.chang.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Slf4j
@Service
public class TestService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestService self;

    @Autowired
    private TestService2 testService2;

    public int test() {
        System.out.println("TestService method test");
        return 0;
    }

    @Transactional
    public void test1() {
        User user = userRepository.findByUserName("haiyang");
        log.error("#######, {}", user);
        user.setNickName("new haiyang");
        userRepository.save(user);
        User user2 = userRepository.findByUserName("haiyang");
        log.error("#######, {}", user2);
        self.test2();

        User user3 = userRepository.findByUserName("haiyang");
        log.error("#######, {}", user3);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void test2() {
        User user = userRepository.findByUserName("haiyang");
        log.error("#######, {}", user);
        user.setNickName("new haiyang222222222");
        userRepository.save(user);
        User user2 = userRepository.findByUserName("haiyang");
        log.error("#######, {}", user2);
    }

    @Transactional
    public void test3() {
        User user = userRepository.findByUserName("haiyang");
        log.error("#######, {}", user);
        user.setNickName("new haiyang3333");
        userRepository.save(user);
        User user2 = userRepository.findByUserName("haiyang");
        log.error("#######, {}", user2);
        testService2.test5();

        User user3 = userRepository.findByUserName("haiyang");
        log.error("#######, {}", user3);
    }



}
