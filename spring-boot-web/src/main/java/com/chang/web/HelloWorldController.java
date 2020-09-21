package com.chang.web;

import com.chang.model.User;
import com.chang.response.CommonResponse;
import com.chang.response.Res;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class HelloWorldController {

    @GetMapping("/intel/hi")
    public CommonResponse hi() {
        CommonResponse<Res> rsp = new CommonResponse<>();
        Res res = new Res();
        res.setOrderBsOri(null);
        res.setOrderBs(null);
        res.setDe(new BigDecimal("0.1234567890"));
        res.setFlag("BTC");
        rsp.setData(res);

        rsp.setErrCode(1);
        rsp.setErrMsg("TOO_BIG");
        return rsp;
    }

}
