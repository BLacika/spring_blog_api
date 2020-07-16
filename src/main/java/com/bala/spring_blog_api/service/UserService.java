package com.bala.spring_blog_api.service;

import com.bala.spring_blog_api.model.User;
import com.bala.spring_blog_api.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    /**
     * Get all User
     */
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    /**
     * Get one User by id
     */
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    /**
     * Get one User by username
     */
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));
    }

    /**
     * Get User by email
     */
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
    }

    /**
     * Create User
     */
    public User createUser(User newUser) {
        newUser.setCreatedAt(LocalDateTime.now());
        newUser.setUpdatedAt(LocalDateTime.now());

        return userRepository.save(newUser);
    }

    /**
     * Update User
     */
    public User updateUser(Long id, User updatedUser) {
        User find = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        find.setUsername(updatedUser.getUsername());
        find.setPassword(updatedUser.getPassword());
        find.setEmail(updatedUser.getEmail());
        find.setRoles(updatedUser.getRoles());
        find.setUpdatedAt(LocalDateTime.now());

        return userRepository.save(find);
    }

    /**
     * Delete User
     */
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
