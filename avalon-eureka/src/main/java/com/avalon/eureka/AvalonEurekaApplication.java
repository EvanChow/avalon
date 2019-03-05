package com.avalon.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class AvalonEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AvalonEurekaApplication.class, args);
    }

}

