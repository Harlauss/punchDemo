package com.harlaus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.harlaus.mapper")
@EnableScheduling
public class AutoPunchApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutoPunchApplication.class, args);
    }

}
