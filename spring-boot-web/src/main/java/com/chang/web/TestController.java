package com.chang.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/authenticate")
    public void unauthorized(HttpServletRequest request, HttpServletResponse response) {
        // 如下的header及内容格式会让浏览器打开输入账号密码的窗口，
        // 输入内容会在request中以key = authorization, value = Basic XXX(XXX 是对账号:密码进行Base64编码的结果)的形式出现
        response.setHeader("WWW-Authenticate", "Basic realm = \"XX\"");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
    }

}
