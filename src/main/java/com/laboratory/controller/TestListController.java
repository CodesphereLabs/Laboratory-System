/**
 * Author : rasintha_j
 * Date : 3/20/2024
 * Time : 12:27 PM
 * Project Name : laboratory
 */

package com.laboratory.controller;

import com.laboratory.model.entity.TestList;
import com.laboratory.service.TestListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TestListController {

    @Autowired
    private TestListService testListService;

    @PostMapping("/test-list/create")
    public TestList createTestList(@RequestBody TestList testList) {
        return testListService.createTestList(testList);
    }

    @GetMapping("/test-list/{id}")
    public TestList getTestListById(@PathVariable String id) {
        return testListService.getTestListById(id);
    }

    @GetMapping("/test-lists")
    public List<TestList> getAllTestLists() {
        return testListService.getAllTestLists();
    }

    @PutMapping("/test-list/{id}")
    public TestList updateTestList(@PathVariable String id, @RequestBody TestList testList) {
        return testListService.updateTestList(id, testList);
    }

    @DeleteMapping("/test-list/{id}")
    public void deleteTestList(@PathVariable String id) {
        testListService.deleteTestList(id);
    }

    @GetMapping("/test-list/{id}")
    public String getDescription(@PathVariable String id) {
        return testListService.getDescriptionById(id);
    }
}