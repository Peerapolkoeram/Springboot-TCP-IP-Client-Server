package com.tpk.tcpclient.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tpk.tcpclient.config.TcpClientGateway;
import com.tpk.tcpclient.dto.MessageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequiredArgsConstructor
public class TcpController {

    private final TcpClientGateway tcpClientGateway;

    @GetMapping(value = "{name}")
    public MessageDTO tcpMessage(@PathVariable("name") String name) {
        byte[] resultTcp = tcpClientGateway.send(name.getBytes());
        String resultString = new String(resultTcp);

        ObjectMapper objectMapper = new ObjectMapper();
        MessageDTO messageDTO;
        try {
            messageDTO = objectMapper.readValue(resultString, MessageDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return messageDTO;
    }

}
