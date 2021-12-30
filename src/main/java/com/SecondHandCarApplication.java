package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lemon.mapper")
public class SecondHandCarApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecondHandCarApplication.class, args);
    }

}
