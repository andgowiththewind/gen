package com.gust.cafe.gen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@MapperScan(basePackages = {"com.gust.cafe.gen.mapper"})
@SpringBootApplication
// @SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}) // 不加载数据库配置
public class GenApplication {

    public static void main(String[] args) {
        SpringApplication.run(GenApplication.class, args);
    }

}
