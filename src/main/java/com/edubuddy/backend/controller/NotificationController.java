package com.edubuddy.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edubuddy.backend.entity.Notification;
import com.edubuddy.backend.service.NotificationService;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "*")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    // Create Notification
    @PostMapping
    public Notification createNotification(
            @RequestBody Notification notification) {

        return notificationService.createNotification(notification);
    }

    // Get All Notifications
    @GetMapping("/{userId}")
    public List<Notification> getNotifications(
            @PathVariable Long userId) {

        return notificationService.getNotifications(userId);
    }

    // Get Unread Notifications
    @GetMapping("/{userId}/unread")
    public List<Notification> getUnreadNotifications(
            @PathVariable Long userId) {

        return notificationService.getUnreadNotifications(userId);
    }

    // Mark as Read
    @PutMapping("/{notificationId}/read")
    public String markAsRead(
            @PathVariable Long notificationId) {

        return notificationService.markAsRead(notificationId);
    }

    // Delete Notification
    @DeleteMapping("/{notificationId}")
    public String deleteNotification(
            @PathVariable Long notificationId) {

        return notificationService.deleteNotification(notificationId);
    }
}