/**
 * Author : rasintha_j
 * Date : 3/20/2024
 * Time : 5:43 PM
 * Project Name : laboratory
 */

package com.laboratory.laboratory.service.impl;

import com.laboratory.laboratory.Repository.TestListRepository;
import com.laboratory.laboratory.model.entity.TestList;
import com.laboratory.laboratory.service.TestListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestListServiceImpl implements TestListService {

    @Autowired
    private TestListRepository testListRepository;

    @Override
    public TestList createTestList(TestList testList) {
        return testListRepository.save(testList);
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
    public void deleteTestList(String id) {
        testListRepository.deleteById(id);
    }

    @Override
    public String getDescriptionById(String id) {
        return testListRepository.findDescriptionById(id);
    }
}
