package com.chang.multi.datasource.controller;

import com.chang.multi.datasource.domain.entity.Cat;
import com.chang.multi.datasource.domain.entity.Dog;
import com.chang.multi.datasource.mapper.primary.CatMapper;
import com.chang.multi.datasource.mapper.secondary.DogMapper;
import com.chang.multi.datasource.service.MixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/both")
public class MixController {

    @Autowired
    private MixService mixService;

    @Autowired
    private CatMapper catMapper;

    @Autowired
    private DogMapper dogMapper;

    @GetMapping("/withPrimaryTxManagerName")
    public String withPrimaryTxManager() {
        int id = 1;
        Cat catOri = catMapper.single(id);
        Dog dogOri = dogMapper.single(id);

        try {
            mixService.rollbackWithPrimaryTxManager(id);
        } catch (Exception ex) {
        }

        Cat catNew = catMapper.single(id);
        Dog dogNew = dogMapper.single(id);

        String res = "";
        if(!catOri.getName().equals(catNew.getName())) {
            res += "cat name is different. ";
        }
        if(!dogOri.getName().equals(dogNew.getName())) {
            res += "dog name is different. ";
        }

        return res;
    }

    @GetMapping("/withSecondaryTxManagerName")
    public String withSecondaryTxManager() {
        int id = 2;
        Cat catOri = catMapper.single(id);
        Dog dogOri = dogMapper.single(id);

        try {
            mixService.rollbackWithSecondaryTxManager(id);
        } catch (Exception ex) {
        }

        Cat catNew = catMapper.single(id);
        Dog dogNew = dogMapper.single(id);

        String res = "";
        if(!catOri.getName().equals(catNew.getName())) {
            res += "cat name is different. ";
        }
        if(!dogOri.getName().equals(dogNew.getName())) {
            res += "dog name is different. ";
        }

        return res;
    }

}
