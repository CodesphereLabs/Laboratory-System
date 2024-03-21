/**
 * Author : rasintha_j
 * Date : 3/20/2024
 * Time : 12:24 PM
 * Project Name : laboratory
 */

package com.laboratory.Repository;


import com.laboratory.model.entity.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends MongoRepository<Client, String> {
    Client findByUsernameAndPassword(String email, String password);
    Client findByUsernameAndRole(String email, String role); // Add method to find user by username and role
}
