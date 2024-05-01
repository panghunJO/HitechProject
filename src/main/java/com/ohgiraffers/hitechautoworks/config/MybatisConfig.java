package com.ohgiraffers.hitechautoworks.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(value = "com.ohgiraffers.hitechautoworks", annotationClass = Mapper.class)
public class MybatisConfig {
}
