package com.chang.web;

import com.chang.response.CommonResponse;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class DefaultErrorController extends AbstractErrorController {


    public DefaultErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping
    @ResponseBody
    public CommonResponse<String> doHandleError() {
        CommonResponse<String> response = new CommonResponse<String>();
        response.setErrCode(0);
        response.setErrMsg("error msg.");
        response.setData("result.");
        return response;
    }
}
