package com.edubuddy.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edubuddy.backend.entity.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    // Get all notifications of a user
    List<Notification> findByUserId(Long userId);

    // Get unread notifications
    List<Notification> findByUserIdAndIsReadFalse(Long userId);

}