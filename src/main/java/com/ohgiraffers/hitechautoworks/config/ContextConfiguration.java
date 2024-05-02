package com.ohgiraffers.hitechautoworks.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@ComponentScan("com.ohgiraffers.hitechautoworks")
@EnableAsync
public class ContextConfiguration {
}
