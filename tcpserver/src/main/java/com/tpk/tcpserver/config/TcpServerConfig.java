package com.tpk.tcpserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.ip.tcp.TcpReceivingChannelAdapter;
import org.springframework.integration.ip.tcp.connection.TcpNetServerConnectionFactory;
import org.springframework.messaging.MessageHandler;

@Configuration
public class TcpServerConfig {

    @Value(value = "${tcp.server.port}")
    private int serverPort;

    @Bean
    public TcpNetServerConnectionFactory connectionFactory() {
        return new TcpNetServerConnectionFactory(serverPort);
    }

    @Bean
    public TcpReceivingChannelAdapter inboundAdapter(TcpNetServerConnectionFactory connectionFactory) {
        TcpReceivingChannelAdapter adapter = new TcpReceivingChannelAdapter();
        adapter.setConnectionFactory(connectionFactory);
        adapter.setOutputChannelName("inputChannel");
        return adapter;
    }

    @Bean
    @ServiceActivator(inputChannel = "inputChannel")
    public MessageHandler handler() {
        return message -> {
            System.out.println("Received: " + message);
            // Handle the received message here
        };
    }

}
