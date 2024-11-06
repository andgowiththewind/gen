package com.gust.cafe.gen;

import com.gust.cafe.gen.util.SqliteUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@tk.mybatis.spring.annotation.MapperScan(basePackages = {"com.gust.cafe.gen.mapper"})
// @SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}) // 不加载数据库配置
public class GenApplication {

    public static void main(String[] args) {
        SqliteUtil.init();
        SpringApplication.run(GenApplication.class, args);
    }

}
