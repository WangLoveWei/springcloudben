package com.example.benauthservice;

import com.example.benauthservice.feign.UserConsumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(clients = {UserConsumer.class})
@EnableEurekaClient
public class BenAuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BenAuthServiceApplication.class, args);
    }

}
