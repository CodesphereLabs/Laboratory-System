/**
 * Author : rasintha_j
 * Date : 3/20/2024
 * Time : 1:39 PM
 * Project Name : laboratory
 */

package com.laboratory.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;

@Document(collection = "history_list")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoryList {
    @Id
    private String id;
    private String appointmentId;
    private int status;
    private String remarks;
    private Timestamp dateCreated;
}
