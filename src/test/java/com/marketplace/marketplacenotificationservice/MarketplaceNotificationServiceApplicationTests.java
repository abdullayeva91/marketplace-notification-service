package com.marketplace.marketplacenotificationservice;

import org.junit.jupiter.api.Test;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@com.marketplace.marketplacenotificationservice.SpringBootTest
class MarketplaceNotificationServiceApplicationTests {

    @Test
    public void test() throws Exception {
        WebSocketStompClient client = new WebSocketStompClient(new StandardWebSocketClient());
        client.setMessageConverter(new MappingJackson2MessageConverter());

        String url = "ws://localhost:8083/ws-notification";
        System.out.println("🔄 Connecting: " + url);

        CountDownLatch latch = new CountDownLatch(1);

        client.connectAsync(url, new StompSessionHandlerAdapter() {
            @Override
            public void afterConnected(StompSession session, StompHeaders headers) {
                System.out.println("✅ CONNECTED!");
                latch.countDown();
            }

            @Override
            public void handleTransportError(StompSession session, Throwable ex) {
                System.err.println("❌ ERROR: " + ex.getMessage());
                ex.printStackTrace();
                latch.countDown();
            }
        });

        latch.await(10, TimeUnit.SECONDS);
    }
}