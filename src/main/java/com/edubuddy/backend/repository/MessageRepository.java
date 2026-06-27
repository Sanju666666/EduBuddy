package com.edubuddy.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edubuddy.backend.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    // Human ↔ Human Chat
    List<Message> findBySenderIdAndReceiverId(Long senderId, Long receiverId);

    // All messages received by a user
    List<Message> findByReceiverId(Long receiverId);

    // All messages sent by a user
    List<Message> findBySenderId(Long senderId);

    // Complete chat history of a user (Human + AI)
    List<Message> findBySenderIdOrReceiverId(Long senderId, Long receiverId);

}