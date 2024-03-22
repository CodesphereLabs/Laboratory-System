/**
 * Author : rasintha_j
 * Date : 3/20/2024
 * Time : 9:44 AM
 * Project Name : laboratory
 */

package com.laboratory.model.entity;

import com.laboratory.util.UserRole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String id;
    private String firstname;
    private String middlename;
    private String lastname;
    private String username;
    private String password;
    private String role;

}


