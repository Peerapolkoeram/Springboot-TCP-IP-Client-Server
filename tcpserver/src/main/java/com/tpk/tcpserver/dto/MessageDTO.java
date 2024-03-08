package com.tpk.tcpserver.dto;

import lombok.Builder;

@Builder
public record MessageDTO (
        String message,
        String timestamp,
        String sender
) {}