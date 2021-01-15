package com.chang.web;

import com.chang.config.CustomMessageSource;
import com.chang.response.CommonResponse;
import com.chang.response.Res;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class HelloWorldController {

//    @Autowired
//    private CustomMessageSource customMessageSource;

    @GetMapping("/intel/hi/{value}")
    public CommonResponse hi(@PathVariable String value) {
        CommonResponse<Res> rsp = new CommonResponse<>();
        Res res = new Res();
        res.setOrderBsOri(null);
        res.setOrderBs(null);
        res.setDe(new BigDecimal("0.1234567890"));
        res.setFlag("BTC");
        rsp.setData(res);

        rsp.setErrCode(1);
        rsp.setErrMsg(value);
        return rsp;
    }

    /*@GetMapping("/add/{key}/{value}")
    public CommonResponse<Boolean> get(@PathVariable String key, @PathVariable String value) {
        customMessageSource.add(LocaleContextHolder.getLocale(), key, value);
        CommonResponse<Boolean> res = new CommonResponse();
        res.setData(Boolean.TRUE);
        return res;
    }*/

}
