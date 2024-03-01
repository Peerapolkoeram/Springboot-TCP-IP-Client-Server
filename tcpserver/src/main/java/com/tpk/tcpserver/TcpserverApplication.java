package com.tpk.tcpserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.config.EnableIntegration;

@EnableIntegration
@SpringBootApplication
public class TcpserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(TcpserverApplication.class, args);
	}


}
