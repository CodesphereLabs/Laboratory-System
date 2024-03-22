/**
 * Author : rasintha_j
 * Date : 3/20/2024
 * Time : 5:43 PM
 * Project Name : laboratory
 */

package com.laboratory.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.laboratory.Repository.TestListRepository;
import com.laboratory.model.entity.TestList;
import com.laboratory.service.TestListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class TestListServiceImpl implements TestListService {

    @Autowired
    private TestListRepository testListRepository;

    @Override
    public TestList createTestList(TestList testList) {
        Optional<TestList> existingTest = testListRepository.findById(testList.getId());
        TestList saveTest = null;
        if (existingTest.isPresent()) {
            System.out.println("User with ID " + testList.getId() + " already exists in the database.");
        } else {
            saveTest = testListRepository.save(testList);
            System.out.println("User with ID " + testList.getId() + " has been added to the database.");
        }
        return saveTest;
    }

    @Override
    public TestList getTestListById(String id) {
        return testListRepository.findById(id).orElse(null);
    }

    @Override
    public List<TestList> getAllTestLists() {
        return testListRepository.findAll();
    }

    @Override
    public TestList updateTestList(String id, TestList testList) {
        TestList existingTestList = testListRepository.findById(id).orElse(null);
        if (existingTestList != null) {
            existingTestList.setName(testList.getName());
            existingTestList.setDescription(testList.getDescription());
            existingTestList.setStatus(testList.getStatus());
            existingTestList.setCost(testList.getCost());
            // Update other fields as needed
            return testListRepository.save(existingTestList);
        } else {
            return null; // or throw an exception if the test list with given id is not found
        }
    }

    @Override
    public int deleteTestList(String id) {
        Optional<TestList> byId = testListRepository.findById(id);

        if (byId.isPresent()) {
            testListRepository.deleteById(id);
            return 1; // User deleted successfully
        } else {
            return 0; // User does not exist, no deletion performed
        }
    }

    @Override
    public String getDescriptionById(String id) {
        String jsonString = testListRepository.findDescriptionById(id);

        try {
            // Parse the JSON string
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonString);

            // Extract the value of the "description" field
            String description = jsonNode.get("description").asText();

            return description;
        } catch (IOException e) {
            // Handle parsing errors
            e.printStackTrace(); // Log the error or handle it as needed
            return null;
        }
    }

    @Override
    public long totalCount() {
        return testListRepository.count();
    }
}
