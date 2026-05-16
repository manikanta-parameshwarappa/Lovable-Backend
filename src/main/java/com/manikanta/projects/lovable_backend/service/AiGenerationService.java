package com.manikanta.projects.lovable_backend.service;

import com.manikanta.projects.lovable_backend.dto.chat.StreamResponse;
import reactor.core.publisher.Flux;

public interface AiGenerationService {
    Flux<String> streamResponse(String message, Long projectId);
}
