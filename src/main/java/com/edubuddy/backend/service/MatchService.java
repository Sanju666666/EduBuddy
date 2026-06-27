package com.edubuddy.backend.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edubuddy.backend.dto.MatchResponse;
import com.edubuddy.backend.entity.User;
import com.edubuddy.backend.repository.UserRepository;

@Service
public class MatchService {

    @Autowired
    private UserRepository userRepository;

    public List<MatchResponse> findMatches(Long userId) {

        // Get Current User
        User currentUser = userRepository.findById(userId).orElse(null);

        if (currentUser == null) {
            return new ArrayList<>();
        }

        // Get All Users
        List<User> allUsers = userRepository.findAll();

        // Final Match List
        List<MatchResponse> matches = new ArrayList<>();

        // Compare with every user
        for (User user : allUsers) {

            // Skip Current User
            if (user.getId().equals(currentUser.getId())) {
                continue;
            }

            int score = 0;

            // Branch Match (30 Marks)
            if (currentUser.getBranch() != null &&
                    currentUser.getBranch().equalsIgnoreCase(user.getBranch())) {

                score += 30;
            }

            // Year Match (20 Marks)
            if (currentUser.getYear() != null &&
                    currentUser.getYear().equalsIgnoreCase(user.getYear())) {

                score += 20;
            }

            // Skills Match (25 Marks)
            if (currentUser.getSkills() != null &&
                    user.getSkills() != null) {

                String currentSkills = currentUser.getSkills().toLowerCase();
                String otherSkills = user.getSkills().toLowerCase();

                String[] skills = currentSkills.split(",");

                for (String skill : skills) {

                    if (otherSkills.contains(skill.trim())) {
                        score += 25;
                        break;
                    }
                }
            }

            // Interests Match (15 Marks)
            if (currentUser.getInterests() != null &&
                    user.getInterests() != null) {

                String currentInterests =
                        currentUser.getInterests().toLowerCase();

                String otherInterests =
                        user.getInterests().toLowerCase();

                String[] interests = currentInterests.split(",");

                for (String interest : interests) {

                    if (otherInterests.contains(interest.trim())) {
                        score += 15;
                        break;
                    }
                }
            }

            // Study Hours Match (10 Marks)
            if (currentUser.getStudyHours() != null &&
                    user.getStudyHours() != null) {

                int diff = Math.abs(
                        currentUser.getStudyHours()
                                - user.getStudyHours());

                if (diff <= 1) {
                    score += 10;
                }
            }

            // Add Match
            matches.add(new MatchResponse(

                    user.getId(),
                    user.getFullName(),
                    user.getBranch(),
                    user.getYear(),
                    score

            ));
        }

        // Sort Highest Score First
        matches.sort(Comparator.comparingInt(
                MatchResponse::getMatchPercentage).reversed());

        return matches;
    }
}