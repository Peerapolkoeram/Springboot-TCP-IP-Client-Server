package com.tpk.tcpserver.service;

import com.tpk.tcpserver.dto.MessageDTO;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MessageService {

    public String process(byte[] message) throws Exception {
        String messageJson = new String(message);

        MessageDTO response = MessageDTO.builder()
                .message("message client: "+ messageJson)
                .timestamp(LocalDateTime.now().toString())
                .sender("server")
                .build();
        return new Jackson2JsonObjectMapper().toJson(response);
    }

}
