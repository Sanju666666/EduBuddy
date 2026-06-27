package com.edubuddy.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AIChatService {

    @Autowired
    private WebClient webClient;

    @Autowired
    private MessageService messageService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public String askAI(String userMessage) {

        String requestBody = """
        {
          "contents": [
            {
              "parts": [
                {
                  "text": "You are EduBuddy AI, a friendly study partner. Help students with coding, DSA, aptitude, machine learning, interview preparation and academics. User question: %s"
                }
              ]
            }
          ]
        }
        """.formatted(userMessage);

        try {

            // Call Gemini API
            String response = webClient.post()
                    .uri("/v1beta/models/gemini-2.5-flash:generateContent")
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            // Parse JSON Response
            JsonNode root = objectMapper.readTree(response);

            String aiResponse = root.path("candidates")
                    .get(0)
                    .path("content")
                    .path("parts")
                    .get(0)
                    .path("text")
                    .asText();

            // Save User Message
            messageService.saveMessage(
                    1L,     // Temporary User ID
                    0L,     // AI
                    userMessage);

            // Save AI Response
            messageService.saveMessage(
                    0L,     // AI
                    1L,     // User
                    aiResponse);

            return aiResponse;

        } catch (Exception e) {

            e.printStackTrace();

            return "Sorry! AI Partner is currently unavailable.\n\nError: "
                    + e.getMessage();
        }
    }
}