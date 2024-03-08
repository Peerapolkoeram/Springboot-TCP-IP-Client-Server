package com.tpk.tcpserver.service;

import com.tpk.tcpserver.dto.MessageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class MessageService {

    public String process(byte[] message) throws Exception {
        String messageJson = new String(message);
        log.info("Receive message as JSON: {}", messageJson);

        MessageDTO response = MessageDTO.builder()
                .message("message client: "+ messageJson)
                .timestamp(LocalDateTime.now().toString())
                .sender("server")
                .build();
        return new Jackson2JsonObjectMapper().toJson(response);
    }

}
