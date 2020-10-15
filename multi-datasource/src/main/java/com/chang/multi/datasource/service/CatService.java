package com.chang.multi.datasource.service;

import com.chang.multi.datasource.domain.entity.Cat;
import com.chang.multi.datasource.mapper.primary.CatMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
public class CatService {

    @Autowired
    private CatMapper catMapper;

    @Transactional
    public void rollbackWithoutTxManagerName(int id) {
        Cat cat = catMapper.single(id);
        cat.setName(UUID.randomUUID().toString());
        cat.setCategory("unknown");
        catMapper.updateById(cat);

        throw new RuntimeException("rollback");
    }

    @Transactional(transactionManager = "primaryTransactionManager")
    public void rollback(int id) {
        Cat cat = catMapper.single(id);
        cat.setName(UUID.randomUUID().toString());
        cat.setCategory("unknown");
        catMapper.updateById(cat);

        throw new RuntimeException("rollback");
    }


}
