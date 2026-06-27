package com.edubuddy.backend.controller;

import java.util.List;
import java.util.Optional;

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

import com.edubuddy.backend.entity.StudyRoom;
import com.edubuddy.backend.service.StudyRoomService;

@RestController
@RequestMapping("/api/studyrooms")
@CrossOrigin(origins = "*")
public class StudyRoomController {

    @Autowired
    private StudyRoomService studyRoomService;

    // Create Room
    @PostMapping
    public StudyRoom createRoom(@RequestBody StudyRoom room) {
        return studyRoomService.createRoom(room);
    }

    // Get All Rooms
    @GetMapping
    public List<StudyRoom> getAllRooms() {
        return studyRoomService.getAllRooms();
    }

    // Get Room By ID
    @GetMapping("/{id}")
    public Optional<StudyRoom> getRoomById(@PathVariable Long id) {
        return studyRoomService.getRoomById(id);
    }

    // Get Rooms By Subject
    @GetMapping("/subject/{subject}")
    public List<StudyRoom> getRoomsBySubject(
            @PathVariable String subject) {

        return studyRoomService.getRoomsBySubject(subject);
    }

    // Join Room
    @PutMapping("/{id}/join")
    public String joinRoom(@PathVariable Long id) {
        return studyRoomService.joinRoom(id);
    }

    // Leave Room
    @PutMapping("/{id}/leave")
    public String leaveRoom(@PathVariable Long id) {
        return studyRoomService.leaveRoom(id);
    }

    // Delete Room
    @DeleteMapping("/{id}")
    public String deleteRoom(@PathVariable Long id) {
        return studyRoomService.deleteRoom(id);
    }
}