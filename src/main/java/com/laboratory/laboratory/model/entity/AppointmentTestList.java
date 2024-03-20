/**
 * Author : rasintha_j
 * Date : 3/20/2024
 * Time : 1:37 PM
 * Project Name : laboratory
 */

package com.laboratory.laboratory.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;

@Document(collection = "appointment_test_list")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentTestList {
    @Id
    private String appointmentId;
    private String testId;
    private Timestamp dateCreated;
}