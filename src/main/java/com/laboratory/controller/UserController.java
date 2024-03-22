/**
 * Author : rasintha_j
 * Date : 3/20/2024
 * Time : 12:27 PM
 * Project Name : laboratory
 */

package com.laboratory.controller;

import com.laboratory.model.bean.ResponseBean;
import com.laboratory.model.entity.Client;
import com.laboratory.model.entity.User;
import com.laboratory.service.ClientService;
import com.laboratory.service.UserService;
import com.laboratory.util.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class UserController {

    ResponseBean responseBean = new ResponseBean();
    @Autowired
    private UserService userService;

    @Autowired
    private ClientService clientService;

    @PostMapping("/register")
    public ResponseBean registerUser(@RequestBody User user) {
        // Perform user registration
        try {
            User registeredUser = userService.registerUser(user);
            if (registeredUser != null) {
                responseBean.setContent(registeredUser);
                responseBean.setResponseCode("200");
                responseBean.setResponseMsg("User registered successfully");
            } else {
                responseBean.setContent(null);
                responseBean.setResponseCode("300");
                responseBean.setResponseMsg("User with ID already exists in the database");
            }
        } catch (Exception e) {
            responseBean.setContent(e);
            responseBean.setResponseCode("500");
            responseBean.setResponseMsg("Failed to register user");
        }
        return responseBean;
    }

//    @PostMapping("/user/login/{username}/{password}")
//    public ResponseBean loginUser(@PathVariable String username, @PathVariable String password) {
//        try {
//            User userUserRole = userService.getUsersByUsernameAndPassword(username, password);
//
//            if (userUserRole != null) {
//                String role = String.valueOf(userUserRole.getRole()); // Assuming getRole() method returns user's role
//                String redirectUrl = determineRedirectUrl(role);
//
//                responseBean.setContent(redirectUrl);
//                responseBean.setResponseCode("200");
//                responseBean.setResponseMsg("User logged in successfully");
//            } else if (clientUserRole != null) {
//                String role = String.valueOf(clientUserRole.getRole()); // Assuming getRole() method returns user's role
//                String redirectUrl = determineRedirectUrl(role);
//
//                responseBean.setContent(redirectUrl);
//                responseBean.setResponseCode("200");
//                responseBean.setResponseMsg("User logged in successfully");
//            } else {
//                responseBean.setContent(null);
//                responseBean.setResponseCode("300");
//                responseBean.setResponseMsg("Invalid username or password");
//            }
//        } catch (Exception e) {
//            responseBean.setContent(e);
//            responseBean.setResponseCode("500");
//            responseBean.setResponseMsg("Internal Server Error");
//        }
//        return responseBean;
//    }

    @PostMapping("/user/login/{username}/{password}")
    public ResponseEntity<String> loginUser(@PathVariable String username, @PathVariable String password) {
        try {
            User user = userService.getUsersByUsernameAndPassword(username, password);
            Client client = clientService.getUsersByUsernameAndPassword(username, password);

            if (user != null) {
                return ResponseEntity.ok("redirect:/admin_dashboard"); // Redirect to admin dashboard
            } else if (client != null) {
                return ResponseEntity.ok("redirect:/client_dashboard"); // Redirect to user dashboard
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    private String determineRedirectUrl(UserRole role) {
        if (UserRole.ADMIN.equals(role)) {
            return "/admin_dashboard.html"; // Redirect to admin dashboard
        } else if (UserRole.CLIENT.equals(role)) {
            return "/client_dashboard.html"; // Redirect to user dashboard
        } else {
            return "/login.html"; // Redirect to login page in case of unknown role
        }
    }

    @PutMapping("/user/{id}")
    public ResponseBean updateUser(@PathVariable String id, @RequestBody User user) {
        try {
            User user1 = userService.updateUser(id, user);
            if (user1 != null) {
                responseBean.setContent(user1);
                responseBean.setResponseCode("200");
                responseBean.setResponseMsg("User update successfully");
            } else {
                responseBean.setContent(null);
                responseBean.setResponseCode("300");
                responseBean.setResponseMsg("Invalid User " + id);
            }
        } catch (Exception e) {
            responseBean.setContent(e);
            responseBean.setResponseCode("500");
            responseBean.setResponseMsg("User update unsuccessfully");
        }
        return responseBean;
    }

    @DeleteMapping("/user/{id}")
    public ResponseBean deleteUser(@PathVariable String id) {
        try {
            int i = userService.deleteUser(id);
            if (i == 1) {
                responseBean.setContent(id);
                responseBean.setResponseCode("200");
                responseBean.setResponseMsg("User delete successfully");
            } else {
                responseBean.setContent(null);
                responseBean.setResponseCode("300");
                responseBean.setResponseMsg("User delete unsuccessfully");
            }
        } catch (Exception e) {
            responseBean.setContent(e);
            responseBean.setResponseCode("500");
            responseBean.setResponseMsg("User delete unsuccessfully");
        }
        return responseBean;
    }

    @GetMapping("/user/{id}")
    public ResponseBean getUserById(@PathVariable String id) {
        try {
            User userById = userService.getUserById(id);
            if (userById != null) {
                responseBean.setContent(userById);
                responseBean.setResponseCode("200");
                responseBean.setResponseMsg("Get user "+ id +" successfully");
            } else {
                responseBean.setContent(null);
                responseBean.setResponseCode("300");
                responseBean.setResponseMsg("Invalid User " + id);
            }
        } catch (Exception e) {
            responseBean.setContent(null);
            responseBean.setResponseCode("500");
            responseBean.setResponseMsg("Get user "+ id +" unsuccessfully");
        }
        return responseBean;
    }

    @GetMapping("/user/users")
    public ResponseBean getAllUsers() {
        try {
            List<User> allUsers = userService.getAllUsers();
            if (allUsers.size() >0 ){
                responseBean.setContent(allUsers);
                responseBean.setResponseCode("200");
                responseBean.setResponseMsg("Get all users fetch successfully");
            } else {
                responseBean.setContent(allUsers);
                responseBean.setResponseCode("300");
                responseBean.setResponseMsg("No users in the database");
            }
        } catch (Exception e) {
            responseBean.setContent(e);
            responseBean.setResponseCode("500");
            responseBean.setResponseMsg("Get all users fetch unsuccessfully");
        }
        return responseBean;
    }
}