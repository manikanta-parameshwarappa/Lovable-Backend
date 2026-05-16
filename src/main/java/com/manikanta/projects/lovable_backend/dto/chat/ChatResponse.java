package com.manikanta.projects.lovable_backend.dto.chat;

import com.manikanta.projects.lovable_backend.enums.MessageRole;

import java.time.Instant;

public record ChatResponse(
        Long id,
        MessageRole role,
        String content,
        Integer tokensUsed,
        Instant createdAt
) {
}
