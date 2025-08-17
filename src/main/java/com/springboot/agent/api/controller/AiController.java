package com.springboot.agent.api.controller;

import com.springboot.agent.api.service.AiAgentService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/ai")
public class AiController {

    private final AiAgentService aiAgentService;

    public AiController(AiAgentService aiAgentService) {
        this.aiAgentService = aiAgentService;
    }

    @PostMapping("/agent")
    public String generateResponse(@RequestBody String prompt) {
        return aiAgentService.getAiResponse(prompt);
    }

    @GetMapping(value = "/chat-stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> generateResponseStream(@RequestParam String prompt) {
        return aiChatService.getAiResponseStream(prompt);
    }
}