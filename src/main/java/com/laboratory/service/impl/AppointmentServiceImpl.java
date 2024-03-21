/**
 * Author : rasintha_j
 * Date : 3/20/2024
 * Time : 4:47 PM
 * Project Name : laboratory
 */

package com.laboratory.service.impl;

import com.laboratory.Repository.AppointmentRepository;
import com.laboratory.Repository.TestListRepository;
import com.laboratory.model.entity.AppointmentList;
import com.laboratory.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    AppointmentList appointment = new AppointmentList();
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private TestListRepository testListRepository;

    @Override
    public AppointmentList createAppointment(String clientId, int testListId, Timestamp schedule, String prescriptionPath) {
        appointment.setClientId(clientId);
        appointment.setSchedule(schedule);
        appointment.setPrescriptionPath(prescriptionPath);
        appointment.setStatus(testListId);
        appointment.setDateCreated(new Timestamp(System.currentTimeMillis()));
        appointment.setDateUpdated(new Timestamp(System.currentTimeMillis()));
        return appointmentRepository.save(appointment);
    }
}

