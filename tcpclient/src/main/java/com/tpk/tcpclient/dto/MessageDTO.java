package com.tpk.tcpclient.dto;

import lombok.Builder;

@Builder
public record MessageDTO(
        String message,
        String timestamp,
        String sender
) {
}
