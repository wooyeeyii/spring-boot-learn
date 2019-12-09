package com.chang.mapper;

import com.chang.enums.UserSexEnum;
import com.chang.model.Person;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonMapperTest {

    @Autowired
    private PersonMapper personMapper;

    @Test
    public void testInsert() throws Exception {
        personMapper.insert(new Person(null, "aa", "a123456", UserSexEnum.MAN, ""));
        personMapper.insert(new Person(null, "bb", "b123456", UserSexEnum.WOMAN, ""));
        personMapper.insert(new Person(null, "cc", "b123456", UserSexEnum.WOMAN, ""));

        Assert.assertEquals(3, personMapper.getAll().size());
    }

    @Test
    public void testQuery() throws Exception {
        List<Person> users = personMapper.getAll();
        if (users == null || users.size() == 0) {
            System.out.println("is null");
        } else {
            System.out.println(users.toString());
        }
    }


    @Test
    public void testUpdate() throws Exception {
        Person user = personMapper.getOne(30l);
        System.out.println(user.toString());
        user.setNickName("chang");
        personMapper.update(user);
        Assert.assertTrue(("chang".equals(personMapper.getOne(30l).getNickName())));
    }
}