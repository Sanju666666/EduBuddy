package com.edubuddy.backend.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edubuddy.backend.entity.Message;
import com.edubuddy.backend.repository.MessageRepository;

@Service
public class ChatService {

    @Autowired
    private MessageRepository messageRepository;

    // Save Message
    public Message sendMessage(Message message) {

        message.setSentAt(LocalDateTime.now());

        return messageRepository.save(message);
    }

    // Get Conversation
    public List<Message> getConversation(Long senderId, Long receiverId) {

        return messageRepository.findBySenderIdAndReceiverId(
                senderId,
                receiverId);
    }

    // Inbox
    public List<Message> getInbox(Long receiverId) {

        return messageRepository.findByReceiverId(receiverId);
    }

}