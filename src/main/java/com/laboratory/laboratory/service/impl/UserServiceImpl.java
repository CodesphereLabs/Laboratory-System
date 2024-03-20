/**
 * Author : rasintha_j
 * Date : 3/20/2024
 * Time : 12:26 PM
 * Project Name : laboratory
 */

package com.laboratory.laboratory.service.impl;

import com.laboratory.laboratory.Repository.UserRepository;
import com.laboratory.laboratory.model.entity.User;
import com.laboratory.laboratory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User loginUser(String email, String password) {
        return userRepository.findByUsernameAndPassword(email,password);
    }

    @Override
    public User loginUserWithRole(String email, String password, String role) {
        return userRepository.findByUsernameAndRole(email, role);
    }

    @Override
    public User updateUser(String id, User user) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            existingUser.setRole(user.getRole());
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            // Set other fields as needed
            return userRepository.save(existingUser);
        }
        return null; // or throw an exception if the user with given id is not found
    }

    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}