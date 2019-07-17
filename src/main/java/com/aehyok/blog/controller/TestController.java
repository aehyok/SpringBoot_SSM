package com.aehyok.blog.controller;

import com.aehyok.blog.model.TestModel;
import com.aehyok.blog.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/test")
@Api(tags = "测试",description="测试文档说明",hidden=true)
public class TestController {
    @Autowired
    private TestService testService;

    @ApiOperation(value = "测试" , httpMethod ="GET", notes = "获取测试数据")
    @GetMapping(value = "/get")
    public List<TestModel> get()
    {
        return testService.getAll();
    }
}
