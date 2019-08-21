package com.avalon.common.avaloncommonzk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AvalonCommonZkApplication {

    public static void main(String[] args) {
        SpringApplication.run(AvalonCommonZkApplication.class, args);
    }

}
