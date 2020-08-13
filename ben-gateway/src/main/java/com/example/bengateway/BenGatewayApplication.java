package com.example.bengateway;

import com.example.bengateway.context.AuthServiceConsumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.context.request.RequestContextListener;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
@EnableFeignClients(clients = {AuthServiceConsumer.class})
public class BenGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(BenGatewayApplication.class, args);
    }

    @Bean
    public RequestContextListener requestContextListener(){
        return new RequestContextListener();
    }

    @Configuration
    public class  SecurityPermitAllConfig extends WebSecurityConfigurerAdapter{
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests().anyRequest().permitAll().and().csrf().disable();
        }
    }
}
