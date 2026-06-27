package com.edubuddy.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edubuddy.backend.entity.StudyRequest;
import com.edubuddy.backend.repository.StudyRequestRepository;

@Service
public class StudyRequestService {

    @Autowired
    private StudyRequestRepository studyRequestRepository;

    // Send Study Request
    public StudyRequest sendRequest(StudyRequest request) {
        request.setStatus("PENDING");
        return studyRequestRepository.save(request);
    }

    // View all requests
    public List<StudyRequest> getAllRequests() {
        return studyRequestRepository.findAll();
    }

    // View requests received by a user
    public List<StudyRequest> getReceivedRequests(Long receiverId) {
        return studyRequestRepository.findByReceiverId(receiverId);
    }

    // View requests sent by a user
    public List<StudyRequest> getSentRequests(Long senderId) {
        return studyRequestRepository.findBySenderId(senderId);
    }

    // Accept Request
    public StudyRequest acceptRequest(Long id) {

        StudyRequest request = studyRequestRepository.findById(id).orElse(null);

        if (request != null) {
            request.setStatus("ACCEPTED");
            return studyRequestRepository.save(request);
        }

        return null;
    }

    // Reject Request
    public StudyRequest rejectRequest(Long id) {

        StudyRequest request = studyRequestRepository.findById(id).orElse(null);

        if (request != null) {
            request.setStatus("REJECTED");
            return studyRequestRepository.save(request);
        }

        return null;
    }
}