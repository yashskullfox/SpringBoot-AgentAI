package com.springboot.agent.api.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;


@Service
public class AiAgentService {

    private final ChatClient chatClient;

    public AiAgentService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public String getAiResponse(String prompt) {
        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }

    public Flux<String> getAiResponseStream(String prompt) {
        return chatClient.prompt()
                .user(prompt)
                .stream() // Change.call() to.stream()
                .content();
    }
}