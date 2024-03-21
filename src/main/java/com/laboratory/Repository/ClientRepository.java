/**
 * Author : rasintha_j
 * Date : 3/20/2024
 * Time : 12:24 PM
 * Project Name : laboratory
 */

package com.laboratory.Repository;


import com.laboratory.model.entity.Client;
import com.laboratory.model.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
public interface ClientRepository extends MongoRepository<Client, String> {
    @Query(value = "{ 'email' : ?0, 'password' : ?1 }", fields = "{ 'role' : 1 }")
    Client findByEmailAndPassword(String email, String password);
}
