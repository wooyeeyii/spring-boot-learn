package com.demo.version.web;

import com.demo.version.domain.vo.SampleReq;
import com.demo.version.domain.vo.SampleRsp;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accept")
@Tag(name = "版本测试Controller")
public class AcceptController {

    @GetMapping(value = "/hello", produces = "application/chang.api.v3.3.0+string")
    public String hello() {
        return "Hello World1111111111";
    }

    @GetMapping(value = "/hello", produces = {"application/chang.api.v3.3.4+string"})
    @ResponseBody
    public String hello2() {
        return "hello word2222222222";
    }

    @GetMapping(value = "/consume", consumes = "application/chang.api.v3.4.0+string")
    @ResponseBody
    @Schema(description = "v3.4.0 API")
    public SampleRsp consume(@RequestBody SampleReq req) {
        return SampleRsp.builder()
                .name(req.getName())
                .sex(0)
                .build();
    }

    @GetMapping(value = "/consume", consumes = "application/chang.api.v3.4.1+string")
    @ResponseBody
    @Schema(description = "v3.4.1 API")
    public SampleRsp consume2(@RequestBody SampleReq req) {
        return SampleRsp.builder()
                .name(req.getName())
                .sex(1)
                .build();
    }

}
