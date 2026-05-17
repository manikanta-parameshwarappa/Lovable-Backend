package com.manikanta.projects.lovable_backend.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.context.annotation.Bean;

public class AiConfig {

    @Bean
    public ChatClient chatClient(ChatClient.Builder builder) {
        return  builder
                .defaultAdvisors(
                        new SimpleLoggerAdvisor() // log LLM requests
                )
                .build();
    }
}
