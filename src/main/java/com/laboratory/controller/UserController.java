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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

    @PostMapping("/user/login/{username}/{password}")
    public ResponseEntity<ResponseBean> loginUser(@PathVariable String username, @PathVariable String password) {
        ResponseBean responseBean = new ResponseBean();
        try {
            User userUserRole = userService.getUsersByUsernameAndPassword(username, password);
            Client clientUserRole = clientService.getUsersByUsernameAndPassword(username, password);

            if (userUserRole != null) {
                String role = String.valueOf(userUserRole.getRole());
                String redirectUrl = determineRedirectUrl(role);
                responseBean.setContent(redirectUrl);
                responseBean.setResponseCode("200");
                responseBean.setResponseMsg("User logged in successfully");
            } else if (clientUserRole != null) {
                // Load the HTML content of client_dashboard.html
                String clientDashboardHtml = loadClientDashboardHtml(); // Load the HTML content
                responseBean.setContent(clientDashboardHtml);
                responseBean.setResponseCode("200");
                responseBean.setResponseMsg("User logged in successfully");
            } else {
                responseBean.setResponseCode("300");
                responseBean.setResponseMsg("Invalid username or password");
            }
        } catch (Exception e) {
            responseBean.setResponseCode("500");
            responseBean.setResponseMsg("Internal Server Error");
        }
        return ResponseEntity.ok().body(responseBean);
    }

    private String loadClientDashboardHtml() {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader("./templates/client_dashboard.html"))) {
            String line;
            while ((line = br.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle file reading errors appropriately
            return "<html><body><h1>Error loading client dashboard</h1></body></html>";
        }
        return contentBuilder.toString();
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


//    public ResponseBean loginUser(@PathVariable String username, @PathVariable String password) {
//        try {
//            User userUserRole = userService.getUsersByUsernameAndPassword(username, password);
//            Client clientUserRole = clientService.getUsersByUsernameAndPassword(username, password);
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