/**
 * Author : rasintha_j
 * Date : 3/20/2024
 * Time : 4:46 PM
 * Project Name : laboratory
 */

package com.laboratory.controller;

import com.laboratory.model.entity.AppointmentList;
import com.laboratory.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
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
        AppointmentList appointment = new AppointmentList();
        //Authentication authentication = null;
        String clientId = null;
        try{
            //authentication = SecurityContextHolder.getContext().getAuthentication();
            //clientId = authentication.getName(); // This will give you the username of the currently authenticated user
            // You can use this username to fetch the user's ID from your database or wherever it's stored
            Timestamp schedule = appointmentRequest.getSchedule();
            String prescriptionPath = appointmentRequest.getPrescriptionPath();
            int testListId = appointmentRequest.getStatus();
            appointment = appointmentService.createAppointment(clientId, testListId, schedule, prescriptionPath);
        } catch (Exception e) {
            System.out.println(e);
        }
        return appointment;
    }
}

