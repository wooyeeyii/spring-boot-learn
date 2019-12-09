package com.chang.service.user;

import org.springframework.beans.factory.config.AbstractFactoryBean;

class UserServiceFactoryBean extends AbstractFactoryBean<UserService> {

    private BeanProperties.User user;

    private UserHandler handler;

    @Override
    public Class<?> getObjectType() {
        return UserService.class;
    }

    @Override
    protected UserService createInstance() throws Exception {
        return new UserService(user, handler);
    }

    public void setUser(BeanProperties.User user) {
        this.user = user;
    }

    public void setHandler(UserHandler handler) {
        this.handler = handler;
    }
}
