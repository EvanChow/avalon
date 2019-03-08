package com.avalon.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class AvalonTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(AvalonTaskApplication.class, args);
    }

}
