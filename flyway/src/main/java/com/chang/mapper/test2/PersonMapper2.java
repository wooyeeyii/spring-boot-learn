package com.chang.mapper.test2;


import com.chang.model.Person;

import java.util.List;

public interface PersonMapper2 {

    List<Person> getAll();

    Person getOne(Long id);

    void insert(Person user);

    void update(Person user);

    void delete(Long id);

}