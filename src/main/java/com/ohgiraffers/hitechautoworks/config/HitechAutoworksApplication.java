package com.ohgiraffers.hitechautoworks.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
public class HitechAutoworksApplication {

    public static void main(String[] args) {
        SpringApplication.run(HitechAutoworksApplication.class, args);
    }

}
