package com.zhm.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
@MapperScan("com.zhm.springcloud.mapper")
public class Main2002 {

    public static void main(String[] args) {
        SpringApplication.run(Main2002.class, args);
    }

}
