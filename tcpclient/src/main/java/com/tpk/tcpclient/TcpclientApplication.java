package com.tpk.tcpclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.config.EnableIntegration;

@EnableIntegration
@SpringBootApplication
public class TcpclientApplication {

    public static void main(String[] args) {
        SpringApplication.run(TcpclientApplication.class, args);
    }

}
