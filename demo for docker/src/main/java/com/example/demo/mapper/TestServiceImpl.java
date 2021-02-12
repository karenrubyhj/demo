package com.example.demo.mapper;


import com.example.demo.dao.TestDao;

import com.example.demo.domain.Test;

import com.example.demo.service.TestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TestServiceImpl implements TestService {


    @Resource
    TestDao testDao;

    @Override
    public List<Test> getAllTest() {
        return testDao.getAllTest();
    }

}
