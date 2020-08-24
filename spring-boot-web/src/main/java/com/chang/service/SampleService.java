package com.chang.service;

import com.chang.model.User;
import org.springframework.stereotype.Service;

@Service
public class SampleService implements SampleInterface {

    @Override
    public User publicMethod(Integer a, String b, User u) {
        User user = validation(a, b, u);
        System.out.println("user = " + user);
        return user;
    }

    private User validation(Integer a, String b, User u) {
        System.out.println("Validation.................");
        User user = new User();
        user.setNickName("yudong");
        return user;
    }
}
