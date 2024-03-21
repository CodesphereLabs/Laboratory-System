/**
 * Author : rasintha_j
 * Date : 3/20/2024
 * Time : 4:48 PM
 * Project Name : laboratory
 */

package com.laboratory.Repository;


import com.laboratory.model.entity.AppointmentList;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AppointmentRepository extends MongoRepository<AppointmentList, String> {
}
