package com.chang.multi.datasource.controller;

import com.chang.multi.datasource.domain.entity.Cat;
import com.chang.multi.datasource.mapper.primary.CatMapper;
import com.chang.multi.datasource.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/primary")
public class CatController {

    @Autowired
    private CatMapper catMapper;

    @Autowired
    private CatService catService;

    @GetMapping("/withoutTxManagerName")
    @ResponseBody
    public boolean withoutTxManagerName() {
        int id = 1;
        Cat catOri = catMapper.single(id);

        try {
            catService.rollbackWithoutTxManagerName(id);
        } catch (Exception ex) {
        }

        Cat catNew = catMapper.single(id);

        return catOri.getName().equals(catNew.getName());
    }

    @GetMapping("/withTxManagerName")
    @ResponseBody
    public boolean withTxManagerName() {
        int id = 2;
        Cat catOri = catMapper.single(id);

        try {
            catService.rollback(id);
        } catch (Exception ex) {
        }

        Cat catNew = catMapper.single(id);

        return catOri.getName().equals(catNew.getName());
    }

}
