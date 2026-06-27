package com.edubuddy.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edubuddy.backend.entity.StudyRoom;

@Repository
public interface StudyRoomRepository extends JpaRepository<StudyRoom, Long> {

    // Find rooms by subject
    List<StudyRoom> findBySubject(String subject);

    // Find rooms created by a user
    List<StudyRoom> findByCreatedBy(Long createdBy);

}