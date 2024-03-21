/**
 * Author : rasintha_j
 * Date : 3/20/2024
 * Time : 1:35 PM
 * Project Name : laboratory
 */

package com.laboratory.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;

@Document(collection = "appointment_list")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentList {
    @Id
    private String id;
    private String code;
    private Timestamp schedule;
    private String clientId;
    private String prescriptionPath;
    private int status;
    private Timestamp dateCreated;
    private Timestamp dateUpdated;
}
