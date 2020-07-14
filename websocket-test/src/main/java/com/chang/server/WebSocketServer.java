package com.chang.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * WebSocket服务端示例
 */
@Slf4j
@ServerEndpoint(value = "/ws/asset")
@Component
public class WebSocketServer {

    private static final AtomicInteger OnlineCount = new AtomicInteger(0);

    private static CopyOnWriteArraySet<Session> SessionSet = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session) {
        SessionSet.add(session);
        int cnt = OnlineCount.incrementAndGet(); // 在线数加1
        log.info("new connection joined，current connection count = {}", cnt);
        SendMessage(session, "connection success! ");
    }

    @OnClose
    public void onClose(Session session) {
        SessionSet.remove(session);
        int cnt = OnlineCount.decrementAndGet();
        log.info("connection closed，current connection count = {}", cnt);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("receive message from client, msg = {}", message);
        SendMessage(session, "message received，msg = {}" + message);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("error occurred. errMsg = {}, sessionId = {}", error.getMessage(), session.getId(), error);
    }

    public static void SendMessage(Session session, String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException ex) {
            log.error("send message failed.", ex);
        }
    }

    public static void BroadCastInfo(String message) {
        for (Session session : SessionSet) {
            if (session.isOpen()) {
                SendMessage(session, message);
            }
        }
    }

    public static void SendMessage(String sessionId, String message) {
        Session session = null;
        for (Session s : SessionSet) {
            if (s.getId().equals(sessionId)) {
                session = s;
                break;
            }
        }
        if (session != null) {
            SendMessage(session, message);
        } else {
            log.warn("sessionId is not fount. sessionId = {}", sessionId);
        }
    }

    /**
     * springboot内置tomcat的话，需要配一下这个.
     * 如果没有这个对象，无法连接到websocket
     * 坑
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        //这个对象说一下，貌似只有服务器是tomcat的时候才需要配置,具体我没有研究
        return new ServerEndpointExporter();
    }

}
