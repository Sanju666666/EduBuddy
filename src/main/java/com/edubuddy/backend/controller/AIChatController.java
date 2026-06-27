package com.edubuddy.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.edubuddy.backend.service.AIChatService;

@RestController
@RequestMapping("/api/ai")
@CrossOrigin(origins = "*")
public class AIChatController {

    @Autowired
    private AIChatService aiChatService;

    @PostMapping("/chat")
    public String chat(@RequestBody String message) {

        return aiChatService.askAI(message);

    }

}