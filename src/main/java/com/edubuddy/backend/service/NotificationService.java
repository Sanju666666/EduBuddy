package com.edubuddy.backend.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edubuddy.backend.entity.Notification;
import com.edubuddy.backend.repository.NotificationRepository;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    // Create Notification
    public Notification createNotification(Notification notification) {

        notification.setCreatedAt(LocalDateTime.now());
        notification.setIsRead(false);

        return notificationRepository.save(notification);
    }

    // Get All Notifications of User
    public List<Notification> getNotifications(Long userId) {

        return notificationRepository.findByUserId(userId);
    }

    // Get Unread Notifications
    public List<Notification> getUnreadNotifications(Long userId) {

        return notificationRepository.findByUserIdAndIsReadFalse(userId);
    }

    // Mark Notification as Read
    public String markAsRead(Long notificationId) {

        Optional<Notification> optional =
                notificationRepository.findById(notificationId);

        if (optional.isEmpty()) {
            return "Notification not found";
        }

        Notification notification = optional.get();

        notification.setIsRead(true);

        notificationRepository.save(notification);

        return "Notification marked as read";
    }

    // Delete Notification
    public String deleteNotification(Long notificationId) {

        if (!notificationRepository.existsById(notificationId)) {
            return "Notification not found";
        }

        notificationRepository.deleteById(notificationId);

        return "Notification deleted successfully";
    }
}