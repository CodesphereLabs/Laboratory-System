/**
 * Author : rasintha_j
 * Date : 3/20/2024
 * Time : 1:33 PM
 * Project Name : laboratory
 */

package com.laboratory.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Document(collection = "test_list")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestList {
    @Id
    private String id;
    private String name;
    private String description;
    private String status;
    private double cost;
    private Date createDate;
}
