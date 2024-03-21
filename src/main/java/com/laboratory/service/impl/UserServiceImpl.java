/**
 * Author : rasintha_j
 * Date : 3/20/2024
 * Time : 12:26 PM
 * Project Name : laboratory
 */

package com.laboratory.service.impl;

import com.laboratory.Repository.UserRepository;
import com.laboratory.model.entity.User;
import com.laboratory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        Optional<User> existingUser = userRepository.findById(user.getId());
        User saveUser = null;
        if (existingUser.isPresent()) {
            System.out.println("User with ID " + user.getId() + " already exists in the database.");
        } else {
            saveUser = userRepository.save(user);
            System.out.println("User with ID " + user.getId() + " has been added to the database.");
        }
        return saveUser;
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
        Optional<User> byIdOptional = userRepository.findById(id);

        if (byIdOptional.isPresent()) {
            User existingUser = byIdOptional.get();
            // Update only the fields that are not the ID
            existingUser.setFirstname(user.getFirstname());
            existingUser.setMiddlename(user.getMiddlename());
            existingUser.setLastname(user.getLastname());
            existingUser.setUsername(user.getUsername());
            existingUser.setPassword(user.getPassword());
            existingUser.setRole(user.getRole());
            // Set other fields as needed
            return userRepository.save(existingUser);
        } else {
            return null; // or throw an exception if the user with the given id is not found
        }
    }

    @Override
    public int deleteUser(String id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            userRepository.deleteById(id);
            return 1; // User deleted successfully
        } else {
            return 0; // User does not exist, no deletion performed
        }
    }

    @Override
    public User getUserById(String id) {
        Optional<User> byId = userRepository.findById(id);

        if (byId.isPresent()) {
            return byId.get(); // Return the user inside the Optional
        } else {
            return null; // Or throw an exception or handle the case according to your application logic
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUsersByUsernameAndPassword(String username, String password) {
        return userRepository.findByEmailAndPassword(username, password);
    }
}