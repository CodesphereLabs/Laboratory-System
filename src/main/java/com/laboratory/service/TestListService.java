/**
 * Author : rasintha_j
 * Date : 3/20/2024
 * Time : 5:43 PM
 * Project Name : laboratory
 */

package com.laboratory.service;

import com.laboratory.model.entity.TestList;

import java.util.List;

public interface TestListService {
    TestList createTestList(TestList testList);
    TestList getTestListById(String id);
    List<TestList> getAllTestLists();
    TestList updateTestList(String id, TestList testList);
    int deleteTestList(String id);
    String getDescriptionById(String id);

    long totalCount();
}
