package com.songhailong.demo1.usercenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Demo1UserCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(Demo1UserCenterApplication.class, args);
    }

}
