package com.edubuddy.backend.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edubuddy.backend.entity.Message;
import com.edubuddy.backend.repository.MessageRepository;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    // Save Message
    public Message saveMessage(Long senderId,
                               Long receiverId,
                               String message) {

        Message chat = new Message();

        chat.setSenderId(senderId);
        chat.setReceiverId(receiverId);
        chat.setMessage(message);
        chat.setSentAt(LocalDateTime.now());

        return messageRepository.save(chat);
    }

    // Chat Between Two Users
    public List<Message> getChat(Long senderId,
                                 Long receiverId) {

        return messageRepository.findBySenderIdAndReceiverId(
                senderId,
                receiverId);
    }

    // All Chats of User
    public List<Message> getUserChats(Long userId) {

        return messageRepository.findByReceiverId(userId);
    }
}