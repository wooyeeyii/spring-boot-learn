package com.chang.mongodb.repository;

import com.chang.model.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PsersonRepository extends MongoRepository<Person, String> {


}
