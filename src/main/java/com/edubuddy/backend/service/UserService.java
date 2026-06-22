package com.edubuddy.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edubuddy.backend.entity.User;
import com.edubuddy.backend.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Signup
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Login
    public String loginUser(User user) {

        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser.isPresent()) {

            if (existingUser.get().getPassword().equals(user.getPassword())) {
                return "Login Successful";
            } else {
                return "Invalid Password";
            }

        }

        return "User Not Found";
    }

    // Get All Users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get User By Id
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Get User By Email
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Update User
    public User updateUser(Long id, User updatedUser) {

        return userRepository.findById(id).map(user -> {

            user.setFullName(updatedUser.getFullName());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());
            user.setCollege(updatedUser.getCollege());
            user.setBranch(updatedUser.getBranch());
            user.setYear(updatedUser.getYear());

            return userRepository.save(user);

        }).orElse(null);
    }

    // Delete User
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}