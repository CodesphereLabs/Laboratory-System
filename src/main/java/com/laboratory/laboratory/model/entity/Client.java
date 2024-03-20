/**
 * Author : rasintha_j
 * Date : 3/20/2024
 * Time : 9:44 AM
 * Project Name : laboratory
 */

package com.laboratory.laboratory.model.entity;

import com.laboratory.laboratory.util.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;
import java.util.Date;

@Document(collection = "clients")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @Id
    private String id;
    private String firstname;
    private String middlename;
    private String lastname;
    private String gender;
    private String contact;
    private String email;
    private String password;
    private String role;
    private Date dob;
    private String address;
    private String avatar;
    private boolean deleteFlag;
    private Timestamp dateCreated;
    private Timestamp dateUpdated;
}


