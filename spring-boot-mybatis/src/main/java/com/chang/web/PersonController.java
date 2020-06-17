package com.chang.web;

import com.chang.mapper.PersonMapper;
import com.chang.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonMapper personMapper;

    @RequestMapping("/getPersons")
    public List<Person> getPersons() {
        List<Person> persons = personMapper.getAll();
        return persons;
    }

    @RequestMapping("/getPerson/{id}")
    public Person getPerson(@PathVariable Long id) {
        Person person = personMapper.getOne(id);
        return person;
    }

    @RequestMapping("/add")
    public void save(Person person) {
        personMapper.insert(person);
    }

    @RequestMapping(value = "update")
    public void update(Person person) {
        personMapper.update(person);
    }

    @RequestMapping(value = "/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        personMapper.delete(id);
    }


}