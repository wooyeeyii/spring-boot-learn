package com.chang.config;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PropertiesTest {

    @Autowired
    private TitleProperties properties;

    @Test
    public void getHello() throws Exception {
        System.out.println(properties.getTitle());
        Assert.assertEquals(properties.getTitle(), "spring");
        Assert.assertEquals(properties.getDescription(), "spring boot learn");
    }

}