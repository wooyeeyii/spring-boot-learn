package com.chang.multi.datasource.service;

import com.chang.multi.datasource.domain.entity.Cat;
import com.chang.multi.datasource.domain.entity.Dog;
import com.chang.multi.datasource.mapper.primary.CatMapper;
import com.chang.multi.datasource.mapper.secondary.DogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class MixService {

    @Autowired
    private CatMapper catMapper;

    @Autowired
    private DogMapper dogMapper;

    @Transactional(transactionManager = "primaryTransactionManager")
    public void rollbackWithPrimaryTxManager(int id) {
        execute(id);
    }


    @Transactional(transactionManager = "secondaryTransactionManager")
    public void rollbackWithSecondaryTxManager(int id) {
        execute(id);
    }

    private void execute(int id) {
        Cat cat = catMapper.single(id);
        cat.setName(UUID.randomUUID().toString());
        cat.setCategory("unknown");
        catMapper.updateById(cat);

        Dog dog = dogMapper.single(id);
        dog.setName(UUID.randomUUID().toString());
        dog.setCategory("unknown");
        dogMapper.updateById(dog);

        throw new RuntimeException("rollback");
    }

}
