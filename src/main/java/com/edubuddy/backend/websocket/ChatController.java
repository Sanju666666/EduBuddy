package com.edubuddy.backend.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.edubuddy.backend.service.MessageService;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private MessageService messageService;

    @MessageMapping("/sendMessage")
    public void sendMessage(@Payload ChatMessage chatMessage) {

        // Save message to MySQL
        messageService.saveMessage(
                chatMessage.getSenderId(),
                chatMessage.getReceiverId(),
                chatMessage.getMessage());

        // Send message to receiver
        messagingTemplate.convertAndSend(
                "/topic/messages/" + chatMessage.getReceiverId(),
                chatMessage);

        // Send message back to sender (update own chat screen)
        messagingTemplate.convertAndSend(
                "/topic/messages/" + chatMessage.getSenderId(),
                chatMessage);
    }
}