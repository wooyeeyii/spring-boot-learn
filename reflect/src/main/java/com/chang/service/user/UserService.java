package com.chang.service.user;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public String getUserName() {
        return user.getName();
    }

}
