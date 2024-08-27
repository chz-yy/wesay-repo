package com.chat.wesay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import com.chat.wesay.controller.WebSocketServer;
@SpringBootApplication
public class WesayApplication {

    public static void main(String[] args) {
        SpringApplication.run(WesayApplication.class, args);
    }

}
