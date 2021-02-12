package com.example.demo.service;

import com.example.demo.domain.PageBean;
import com.example.demo.domain.USER;

import java.sql.SQLException;
import java.util.List;

public interface UserService {


   //获取全部用户


    List<USER> getAllUser();



    //获取全部用户条数


    int  getTotalCount();



    //获取全部用户分页

    List<USER> UserListForPage(int index, int currentCount);

    //根据名称获取用户分页

    List<USER> getbynameforpage(String name,int index, int currentCount);




    //获取分页数据总函数
     PageBean findPageBean(String name,int currentPage, int currentCount) throws SQLException;



    void insert(USER user);


    List<USER>  getbyName(String name);

    void delete(int id);

    void update(USER user);



}
