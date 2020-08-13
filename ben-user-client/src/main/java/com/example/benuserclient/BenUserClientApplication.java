package com.example.benuserclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BenUserClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(BenUserClientApplication.class, args);
    }

}
