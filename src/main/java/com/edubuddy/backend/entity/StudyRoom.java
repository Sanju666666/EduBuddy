package com.edubuddy.backend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "study_rooms")
public class StudyRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomName;

    private String subject;

    private Long createdBy;

    private Integer maxMembers;

    private Integer currentMembers;

    private LocalDateTime createdAt;

    public StudyRoom() {
    }

    public StudyRoom(Long id,
                     String roomName,
                     String subject,
                     Long createdBy,
                     Integer maxMembers,
                     Integer currentMembers,
                     LocalDateTime createdAt) {

        this.id = id;
        this.roomName = roomName;
        this.subject = subject;
        this.createdBy = createdBy;
        this.maxMembers = maxMembers;
        this.currentMembers = currentMembers;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getMaxMembers() {
        return maxMembers;
    }

    public void setMaxMembers(Integer maxMembers) {
        this.maxMembers = maxMembers;
    }

    public Integer getCurrentMembers() {
        return currentMembers;
    }

    public void setCurrentMembers(Integer currentMembers) {
        this.currentMembers = currentMembers;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}