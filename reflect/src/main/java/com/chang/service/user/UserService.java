package com.chang.service.user;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class UserService {

    private BeanProperties.User user;

    private UserHandler handler;

    public UserService(BeanProperties.User user, UserHandler handler) {
        this.user = user;
        this.handler = handler;
    }

    public String getUserInfo() {
        invokeHandler();
        return user.toString();
    }

    private void invokeHandler() {
        String methodName = user.getName() + "Handler";
        try {
            Method m = handler.getClass().getMethod(methodName);
            m.invoke(handler);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
            log.error("", ex);
        }
    }

    public String getUserName() {
        return user.getName();
    }

}
