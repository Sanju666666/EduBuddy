package com.edubuddy.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edubuddy.backend.entity.StudyRequest;

@Repository
public interface StudyRequestRepository extends JpaRepository<StudyRequest, Long> {

    List<StudyRequest> findByReceiverId(Long receiverId);

    List<StudyRequest> findBySenderId(Long senderId);
}