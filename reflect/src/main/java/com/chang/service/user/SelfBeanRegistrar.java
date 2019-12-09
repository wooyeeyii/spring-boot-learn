package com.chang.service.user;

import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.source.ConfigurationPropertySources;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

public class SelfBeanRegistrar implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    private Environment environment;
    private BeanProperties properties;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        bindProperties();
        registerSelfBeans(metadata, registry);
    }

    private void bindProperties() {
        Binder binder = new Binder(ConfigurationPropertySources.from(((ConfigurableEnvironment) environment).getPropertySources()));
        properties = binder.bind("self.bean.props", BeanProperties.class).get();
    }

    private void registerSelfBeans(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        System.out.println("bean.props" + properties);
        properties.getUsers().forEach((name, user) -> {
            BeanDefinitionBuilder bdb = BeanDefinitionBuilder.genericBeanDefinition(UserServiceFactoryBean.class);

            bdb.addPropertyValue("user", user);
            bdb.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);

            AbstractBeanDefinition definition = bdb.getBeanDefinition();
            BeanDefinitionHolder holder = new BeanDefinitionHolder(definition, user.getName() + UserService.class.getSimpleName());
            BeanDefinitionReaderUtils.registerBeanDefinition(holder, registry);
        });
    }

}
