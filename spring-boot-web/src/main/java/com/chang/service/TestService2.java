package com.chang.service;

import com.chang.model.User;
import com.chang.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class TestService2 {

    @Autowired
    private UserRepository userRepository;


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void test5() {
        User user = userRepository.findByUserName("haiyang");
        log.error("####### inner, {}", user);

        throw new IllegalArgumentException("");
    }

}
