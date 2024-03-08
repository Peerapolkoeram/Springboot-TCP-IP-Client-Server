package com.tpk.tcpserver.messaging;

import com.tpk.tcpserver.config.TcpServerConfig;
import com.tpk.tcpserver.service.MessageService;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;

@MessageEndpoint
public class TCPMessageEndpoint {

    private final MessageService messageService;

    public TCPMessageEndpoint(MessageService messageService) {
        this.messageService = messageService;
    }

    @ServiceActivator(inputChannel = TcpServerConfig.outputChannel)
    public byte[] process(byte[] message) throws Exception {
        String response = messageService.process(message);
        return response.getBytes();
    }

}
