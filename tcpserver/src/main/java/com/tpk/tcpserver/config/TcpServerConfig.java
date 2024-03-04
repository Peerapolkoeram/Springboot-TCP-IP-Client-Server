package com.tpk.tcpserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.ip.tcp.TcpReceivingChannelAdapter;
import org.springframework.integration.ip.tcp.connection.TcpNetServerConnectionFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.support.MessageBuilder;

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
            Message<String> responseMessage = MessageBuilder.withPayload("Response message").build();
            outputChannel().send(responseMessage);
        };
    }

    @Bean
    public MessageChannel outputChannel() {
        return new DirectChannel();
    }

    @ServiceActivator(inputChannel = "outputChannel")
    public void handleMessage(Message<?> message) {
        System.out.println(message.getPayload());
    }

}
