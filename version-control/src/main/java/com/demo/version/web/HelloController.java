package com.demo.version.web;

import com.demo.version.anno.ApiVersion;
import com.demo.version.anno.HQAuthorization;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api-version")
public class HelloController {

    @ApiVersion("1.0.0")
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String helloV1() {
        return "1.0.0";
    }

    @ApiVersion("1.1.0")
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String helloV2() {
        return "1.1.0";
    }


    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    @ResponseBody
    public String hi() {
        return "hikongkongruye.";
    }

    @ApiVersion("1.1.0")
    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    @ResponseBody
    public String hiV1() {
        return "1.1.0";
    }

    @ApiVersion("1.2.0")
    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    @ResponseBody
    public String hiV2() {
        return "1.2.0";
    }

    @HQAuthorization
    @RequestMapping(value = "/nihao", method = RequestMethod.GET)
    @ResponseBody
    public String nihao() {
        return "nihaokongkongruye.";
    }

}
