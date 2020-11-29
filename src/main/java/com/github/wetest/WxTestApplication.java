package com.github.wetest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class WxTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxTestApplication.class, args);
    }

}
