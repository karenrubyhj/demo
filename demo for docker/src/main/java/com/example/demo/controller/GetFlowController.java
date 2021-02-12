package com.example.demo.controller;


import com.example.demo.domain.Test;
import com.example.demo.domain.USER;
import com.example.demo.service.TestService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class GetFlowController {


    @Autowired
    TestService testService;


    @GetMapping(value = "/getflow")

    public List<Test> getflow() {


        return testService.getAllTest();


    }



}
