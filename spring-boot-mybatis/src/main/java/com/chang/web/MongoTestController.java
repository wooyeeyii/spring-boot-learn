package com.chang.web;

import com.chang.enums.UserSexEnum;
import com.chang.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mongo")
public class MongoTestController {

    @Autowired
    MongoTemplate mongotemplate;

    @RequestMapping("/get")
    public List<Person> getPersons() {
        Query query = new Query(Criteria.where("nickName").is("dong"));
        List<Person> persons = mongotemplate.find(query, Person.class);
        return persons;
    }

    @RequestMapping("/set")
    public Person setPerson() {
        Person person = Person.builder()
                .id(1L)
                .nickName("dong")
                .passWord("yu")
                .userName("lin")
                .userSex(UserSexEnum.MAN)
                .build();
        mongotemplate.save(person);

        Person person2 = Person.builder()
                .id(2L)
                .nickName("dong")
                .passWord("yuu")
                .userName("linnnnnn")
                .userSex(UserSexEnum.MAN)
                .build();
        mongotemplate.save(person2);
        return person;
    }
}