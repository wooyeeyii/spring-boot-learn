package com.chang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;

@Controller
public class IndexController {

    @RequestMapping("/index")
    public String hello(Locale locale, Model model) {
        return "index";
    }

}