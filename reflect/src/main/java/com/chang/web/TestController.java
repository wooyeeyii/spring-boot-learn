package com.chang.web;

import com.chang.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class TestController {

    @Autowired
    private Collection<UserService> userServices;

    @RequestMapping(value = "/user/info/{name}", method = RequestMethod.GET)
    public String getUserInfo(@PathVariable String name) {
        for (UserService service : userServices) {
            if (service.getUserName().contains(name)) {
                return service.getUserInfo();
            }
        }
        return "not found";
    }
}
