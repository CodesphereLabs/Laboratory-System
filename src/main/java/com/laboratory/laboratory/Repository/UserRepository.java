/**
 * Author : rasintha_j
 * Date : 3/20/2024
 * Time : 12:24 PM
 * Project Name : laboratory
 */

package com.laboratory.laboratory.Repository;


import com.laboratory.laboratory.model.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByUsernameAndPassword(String email, String password);
    User findByUsernameAndRole(String email, String role); // Add method to find user by username and role
}
