/**
 * Author : rasintha_j
 * Date : 3/20/2024
 * Time : 12:25 PM
 * Project Name : laboratory
 */

package com.laboratory.service;

import com.laboratory.model.entity.User;

import java.util.List;

public interface UserService {
    User registerUser(User user);
    User loginUser(String email, String password);
    User loginUserWithRole(String email, String password, String role); // Add method for login with role
    User updateUser(String id, User user); // Add updateUser method
    int deleteUser(String id);
    User getUserById(String id);
    List<User> getAllUsers();

    User getUsersByUsernameAndPassword(String username, String password);
}
