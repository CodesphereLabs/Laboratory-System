/**
 * Author : rasintha_j
 * Date : 3/20/2024
 * Time : 4:47 PM
 * Project Name : laboratory
 */

package com.laboratory.service;


import com.laboratory.model.entity.AppointmentList;

import java.sql.Timestamp;

public interface AppointmentService {
    AppointmentList createAppointment(String clientId, int testListId, Timestamp schedule, String prescriptionPath);

    AppointmentList saveAppointment(AppointmentList appointmentList);
}
