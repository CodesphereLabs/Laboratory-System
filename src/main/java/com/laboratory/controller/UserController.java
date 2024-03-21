/**
 * Author : rasintha_j
 * Date : 3/20/2024
 * Time : 12:27 PM
 * Project Name : laboratory
 */

package com.laboratory.controller;

import com.laboratory.model.entity.User;
import com.laboratory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        // Perform user registration
        User registeredUser = userService.registerUser(user);
        if (registeredUser != null) {
            return ResponseEntity.ok().body("User registered successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to register user");
        }
    }

    // Class for response with redirect URL
    private static class RedirectResponse {
        private String redirectUrl;

        public RedirectResponse(String redirectUrl) {
            this.redirectUrl = redirectUrl;
        }

        public String getRedirectUrl() {
            return redirectUrl;
        }

        public void setRedirectUrl(String redirectUrl) {
            this.redirectUrl = redirectUrl;
        }
    }

    @PostMapping("/user/login")
    public ResponseEntity<String> loginUser(@RequestParam String username, @RequestParam String password) {
        // Validate the user's credentials and retrieve their role
        User user = userService.loginUser(username, password);
        if (user != null) {
            String role = String.valueOf(user.getRole()); // Assuming getRole() method returns user's role
            String redirectUrl = determineRedirectUrl(role);
            return ResponseEntity.ok().body(redirectUrl);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    private String determineRedirectUrl(String role) {
        // Determine the redirect URL based on the user's role
        if ("admin".equals(role)) {
            return "/admin_dashboard.html"; // Redirect to admin dashboard
        } else if ("client".equals(role)) {
            return "/client_dashboard.html"; // Redirect to user dashboard
        } else {
            return "/login.html"; // Redirect to login page in case of unknown role
        }
    }

    @PostMapping("/user/loginWithRole")
    public User loginUserWithRole(@RequestParam String username, @RequestParam String password, @RequestParam String role) {
        return userService.loginUserWithRole(username, password, role);
    }

    @PutMapping("/user/{id}")
    public User updateUser(@PathVariable String id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @GetMapping("/user/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}