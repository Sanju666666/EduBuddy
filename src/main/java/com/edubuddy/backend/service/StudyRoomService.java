package com.edubuddy.backend.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edubuddy.backend.entity.StudyRoom;
import com.edubuddy.backend.repository.StudyRoomRepository;

@Service
public class StudyRoomService {

    @Autowired
    private StudyRoomRepository studyRoomRepository;

    // Create Study Room
    public StudyRoom createRoom(StudyRoom room) {

        room.setCurrentMembers(1);
        room.setCreatedAt(LocalDateTime.now());

        return studyRoomRepository.save(room);
    }

    // Get All Rooms
    public List<StudyRoom> getAllRooms() {
        return studyRoomRepository.findAll();
    }

    // Get Room By ID
    public Optional<StudyRoom> getRoomById(Long id) {
        return studyRoomRepository.findById(id);
    }

    // Get Rooms By Subject
    public List<StudyRoom> getRoomsBySubject(String subject) {
        return studyRoomRepository.findBySubject(subject);
    }

    // Join Room
    public String joinRoom(Long roomId) {

        Optional<StudyRoom> optionalRoom =
                studyRoomRepository.findById(roomId);

        if (optionalRoom.isEmpty()) {
            return "Study Room not found";
        }

        StudyRoom room = optionalRoom.get();

        if (room.getCurrentMembers() >= room.getMaxMembers()) {
            return "Study Room is Full";
        }

        room.setCurrentMembers(room.getCurrentMembers() + 1);

        studyRoomRepository.save(room);

        return "Joined Study Room Successfully";
    }

    // Leave Room
    public String leaveRoom(Long roomId) {

        Optional<StudyRoom> optionalRoom =
                studyRoomRepository.findById(roomId);

        if (optionalRoom.isEmpty()) {
            return "Study Room not found";
        }

        StudyRoom room = optionalRoom.get();

        if (room.getCurrentMembers() > 0) {
            room.setCurrentMembers(room.getCurrentMembers() - 1);
        }

        studyRoomRepository.save(room);

        return "Left Study Room Successfully";
    }

    // Delete Room
    public String deleteRoom(Long roomId) {

        if (!studyRoomRepository.existsById(roomId)) {
            return "Study Room not found";
        }

        studyRoomRepository.deleteById(roomId);

        return "Study Room Deleted Successfully";
    }
}