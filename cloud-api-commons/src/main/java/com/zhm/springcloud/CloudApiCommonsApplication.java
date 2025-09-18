package com.zhm.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.ZonedDateTime;

@SpringBootApplication
public class CloudApiCommonsApplication {

    public static void main(String[] args) {

        ZonedDateTime now = ZonedDateTime.now();
        System.out.println(now);
    }

}
