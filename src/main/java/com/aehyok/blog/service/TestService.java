package com.aehyok.blog.service;

import com.aehyok.blog.mapper.TestMapper;
import com.aehyok.blog.model.TestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    @Autowired
    private TestMapper testMapper;

    public List<TestModel> getAll(){
        return testMapper.getAll();
    }
}
