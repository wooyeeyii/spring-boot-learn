package com.chang.server;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class SendMessageService {

    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

    @PostConstruct
    public void init() {
        String message = "{\"channel\":\"PRICE_TICKER\",\"data\":{}}";
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            WebSocketServer.BroadCastInfo(message);
        }, 0L, 1L, TimeUnit.SECONDS);
    }

}
