package com.aehyok.blog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/test")
public class TestController {
    @GetMapping(value = "/get")
    public List<String> get()
    {
        List<String> list=new ArrayList<>();
        list.add("aehyok");
        list.add("teemo");
        list.add("jolinson");
        return list;
    }
}
