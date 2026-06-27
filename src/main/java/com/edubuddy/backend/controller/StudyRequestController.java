package com.edubuddy.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edubuddy.backend.entity.StudyRequest;
import com.edubuddy.backend.service.StudyRequestService;

@RestController
@RequestMapping("/api/study-requests")
@CrossOrigin(origins = "*")
public class StudyRequestController {

    @Autowired
    private StudyRequestService studyRequestService;

    // Send Request
    @PostMapping
    public StudyRequest sendRequest(@RequestBody StudyRequest request) {
        return studyRequestService.sendRequest(request);
    }

    // Get All Requests
    @GetMapping
    public List<StudyRequest> getAllRequests() {
        return studyRequestService.getAllRequests();
    }

    // Get Received Requests
    @GetMapping("/received/{receiverId}")
    public List<StudyRequest> getReceivedRequests(@PathVariable Long receiverId) {
        return studyRequestService.getReceivedRequests(receiverId);
    }

    // Get Sent Requests
    @GetMapping("/sent/{senderId}")
    public List<StudyRequest> getSentRequests(@PathVariable Long senderId) {
        return studyRequestService.getSentRequests(senderId);
    }

    // Accept Request
    @PutMapping("/accept/{id}")
    public StudyRequest acceptRequest(@PathVariable Long id) {
        return studyRequestService.acceptRequest(id);
    }

    // Reject Request
    @PutMapping("/reject/{id}")
    public StudyRequest rejectRequest(@PathVariable Long id) {
        return studyRequestService.rejectRequest(id);
    }
}