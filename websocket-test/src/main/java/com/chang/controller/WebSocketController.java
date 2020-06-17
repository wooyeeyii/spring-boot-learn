package com.chang.controller;


import com.chang.server.WebSocketServer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * WebSocket服务器端推送消息示例Controller
 */
@Controller
@RequestMapping("/message")
public class WebSocketController {

    @RequestMapping(value = "/sendAll", method = RequestMethod.GET)
    @ResponseBody
    String sendAllMessage(@RequestParam(required = true) String message) {
        WebSocketServer.BroadCastInfo(message);
        return "ok";
    }

    @RequestMapping(value = "/sendOne", method = RequestMethod.GET)
    String sendOneMessage(@RequestParam(required = true) String message, @RequestParam(required = true) String id) {
        WebSocketServer.SendMessage(id, message);
        return "ok";
    }
}
