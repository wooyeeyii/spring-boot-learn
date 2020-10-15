package com.chang.multi.datasource.controller;

import com.chang.multi.datasource.domain.entity.Dog;
import com.chang.multi.datasource.mapper.secondary.DogMapper;
import com.chang.multi.datasource.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("secondary")
public class DogController {

    @Autowired
    private DogMapper dogMapper;

    @Autowired
    private DogService dogService;

    @GetMapping("/withoutTxManagerName")
    @ResponseBody
    public boolean withoutTxManagerName() {
        int id = 1;
        Dog dogOri = dogMapper.single(id);

        try {
            dogService.rollbackWithoutTxManagerName(id);
        } catch (Exception ex) {
        }

        Dog dogNew = dogMapper.single(id);

        return dogOri.getName().equals(dogNew.getName());
    }

    @GetMapping("/withTxManagerName")
    @ResponseBody
    public boolean withSecondaryTxManagerName() {
        int id = 2;
        Dog dogOri = dogMapper.single(id);

        try {
            dogService.rollback(id);
        } catch (Exception ex) {
        }

        Dog dogNew = dogMapper.single(id);

        return dogOri.getName().equals(dogNew.getName());
    }

}
