package com.example.demomongodb.service;

import com.example.demomongodb.domain.TestDocument;
import com.example.demomongodb.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Michael
 * @date: 3/15/2022 9:54 AM
 */
@Service
public class TestService {

    private TestRepository testRepository;

    @Autowired
    public void setTestRepository(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public List<TestDocument> getAll(){
        return testRepository.findAll();
    }
}
