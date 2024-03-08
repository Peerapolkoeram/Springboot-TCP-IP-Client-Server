package com.tpk.tcpclient.config;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.stereotype.Component;

@Component
@MessagingGateway(defaultRequestChannel = "outputChannel")
public interface TcpClientGateway {

    byte[] send(byte[] message);

}
