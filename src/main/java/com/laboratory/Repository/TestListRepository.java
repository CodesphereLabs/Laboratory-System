/**
 * Author : rasintha_j
 * Date : 3/20/2024
 * Time : 5:21 PM
 * Project Name : laboratory
 */

package com.laboratory.Repository;

import com.laboratory.model.entity.TestList;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;

@EnableMongoRepositories
public interface TestListRepository extends MongoRepository<TestList, String> {
        List<TestList> findByIdIn(int ids);

        @Query(value = "{'id': ?0}", fields = "{'description' : 1}")
        String findDescriptionById(String id);

        long count();
}
