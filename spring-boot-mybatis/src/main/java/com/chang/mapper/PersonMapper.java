package com.chang.mapper;


import com.chang.model.Person;

import java.util.List;

public interface PersonMapper {

    List<Person> getAll();

    Person getOne(Long id);

    void insert(Person user);

    void update(Person user);

    void delete(Long id);

}