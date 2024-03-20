/**
 * Author : rasintha_j
 * Date : 3/20/2024
 * Time : 4:46 PM
 * Project Name : laboratory
 */

package com.laboratory.laboratory.controller;

import com.laboratory.laboratory.model.entity.AppointmentList;
import com.laboratory.laboratory.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/create")
    public AppointmentList createAppointment(@RequestBody AppointmentList appointmentRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String clientId = authentication.getName(); // This will give you the username of the currently authenticated user
        // You can use this username to fetch the user's ID from your database or wherever it's stored
        Timestamp schedule = appointmentRequest.getSchedule();
        String prescriptionPath = appointmentRequest.getPrescriptionPath();
        int testListId = appointmentRequest.getStatus();
        return appointmentService.createAppointment(clientId, testListId, schedule, prescriptionPath);
    }
}

