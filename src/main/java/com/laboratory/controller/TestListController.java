/**
 * Author : rasintha_j
 * Date : 3/20/2024
 * Time : 12:27 PM
 * Project Name : laboratory
 */

package com.laboratory.controller;

import com.laboratory.model.bean.ResponseBean;
import com.laboratory.model.entity.TestList;
import com.laboratory.model.entity.User;
import com.laboratory.service.TestListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class TestListController {

    @Autowired
    private TestListService testListService;
    ResponseBean responseBean = new ResponseBean();

    @PostMapping("/test-list/create")
    public ResponseBean createTestList(@RequestBody TestList testList) {
        try {
            TestList testList1 = testListService.createTestList(testList);
            if (testList1 != null) {
                responseBean.setContent(testList1);
                responseBean.setResponseCode("200");
                responseBean.setResponseMsg("Test create successfully");
            } else {
                responseBean.setContent(null);
                responseBean.setResponseCode("300");
                responseBean.setResponseMsg("Test already exists in the database");
            }
        } catch (Exception e) {
            responseBean.setContent(e);
            responseBean.setResponseCode("500");
            responseBean.setResponseMsg("Failed to create test");
        }
        return responseBean;
    }

    @GetMapping("/test-list/{id}")
    public ResponseBean getTestListById(@PathVariable String id) {
        try {
            TestList testListById = testListService.getTestListById(id);
            if (testListById != null) {
                responseBean.setContent(testListById);
                responseBean.setResponseCode("200");
                responseBean.setResponseMsg("Get Test "+ id +" successfully");
            } else {
                responseBean.setContent(null);
                responseBean.setResponseCode("300");
                responseBean.setResponseMsg("Invalid Test " + id);
            }
        } catch (Exception e) {
            responseBean.setContent(null);
            responseBean.setResponseCode("500");
            responseBean.setResponseMsg("Get Test "+ id +" unsuccessfully");
        }
        return responseBean;
    }

    @GetMapping("/test-lists")
    public ResponseBean getAllTestLists() {
        try {
            List<TestList> allTestLists = testListService.getAllTestLists();
            if (allTestLists.size() >0 ){
                responseBean.setContent(allTestLists);
                responseBean.setResponseCode("200");
                responseBean.setResponseMsg("Get all test fetch successfully");
            } else {
                responseBean.setContent(allTestLists);
                responseBean.setResponseCode("300");
                responseBean.setResponseMsg("No test in the database");
            }
        } catch (Exception e) {
            responseBean.setContent(e);
            responseBean.setResponseCode("500");
            responseBean.setResponseMsg("Get all test fetch unsuccessfully");
        }
        return responseBean;
    }

    @PutMapping("/test-list/{id}")
    public ResponseBean updateTestList(@PathVariable String id, @RequestBody TestList testList) {
        try {
            TestList testList1 = testListService.updateTestList(id, testList);
            if (testList1 != null) {
                responseBean.setContent(testList1);
                responseBean.setResponseCode("200");
                responseBean.setResponseMsg("Test update successfully");
            } else {
                responseBean.setContent(null);
                responseBean.setResponseCode("300");
                responseBean.setResponseMsg("Invalid Test " + id);
            }
        } catch (Exception e) {
            responseBean.setContent(e);
            responseBean.setResponseCode("500");
            responseBean.setResponseMsg("Test update unsuccessfully");
        }
        return responseBean;
    }

    @DeleteMapping("/test-list/{id}")
    public ResponseBean deleteTestList(@PathVariable String id) {
        try {
            int i = testListService.deleteTestList(id);
            if (i == 1) {
                responseBean.setContent(id);
                responseBean.setResponseCode("200");
                responseBean.setResponseMsg("Test delete successfully");
            } else {
                responseBean.setContent(null);
                responseBean.setResponseCode("300");
                responseBean.setResponseMsg("Test delete unsuccessfully");
            }
        } catch (Exception e) {
            responseBean.setContent(e);
            responseBean.setResponseCode("500");
            responseBean.setResponseMsg("Test delete unsuccessfully");
        }
        return responseBean;
    }

    @GetMapping("/test-list-des/{id}")
    public ResponseBean getDescription(@PathVariable String id) {
        try {
            String descriptionById = testListService.getDescriptionById(id);
            System.out.println(descriptionById);
            if (descriptionById != null) {
                responseBean.setContent(descriptionById);
                responseBean.setResponseCode("200");
                responseBean.setResponseMsg("Test description fetch successfully");
            } else {
                responseBean.setContent(null);
                responseBean.setResponseCode("300");
                responseBean.setResponseMsg("Invalid Test " + id);
            }
        } catch (Exception e) {
            responseBean.setContent(e);
            responseBean.setResponseCode("500");
            responseBean.setResponseMsg("Test description fetch unsuccessfully");
        }
        return responseBean;
    }

    @GetMapping("/test-count")
    public ResponseBean getTitalCount() {
        try {
            long count = testListService.totalCount();
            if (count > 0) {
                responseBean.setContent(String.valueOf(count));
                responseBean.setResponseCode("200");
                responseBean.setResponseMsg("Total Test fetch successfully");
            } else {
                responseBean.setContent(null);
                responseBean.setResponseCode("300");
                responseBean.setResponseMsg("Not able Test count");
            }
        } catch (Exception e) {
            responseBean.setContent(e);
            responseBean.setResponseCode("500");
            responseBean.setResponseMsg("otal Test fetch unsuccessfully");
        }
        return responseBean;
    }
}