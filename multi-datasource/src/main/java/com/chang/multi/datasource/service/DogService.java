package com.chang.multi.datasource.service;

import com.chang.multi.datasource.domain.entity.Dog;
import com.chang.multi.datasource.mapper.secondary.DogMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
public class DogService {
    
    @Autowired
    private DogMapper dogMapper;

    @Transactional
    public void rollbackWithoutTxManagerName(int id) {
        Dog dog = dogMapper.single(id);
        dog.setName(UUID.randomUUID().toString());
        dog.setCategory("unknown");
        dogMapper.updateById(dog);

        throw new RuntimeException("rollback");
    }

    @Transactional(transactionManager = "secondaryTransactionManager")
    public void rollback(int id) {
        Dog dog = dogMapper.single(id);
        dog.setName(UUID.randomUUID().toString());
        dog.setCategory("unknown");
        dogMapper.updateById(dog);

        throw new RuntimeException("rollback");
    }
    
}
