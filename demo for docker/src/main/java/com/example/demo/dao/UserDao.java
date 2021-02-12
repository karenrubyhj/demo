package com.example.demo.dao;


import com.example.demo.domain.USER;

import java.util.List;

public interface UserDao {

   //获取全部用户
    List<USER> getAllUser();

  //获取全部数据条数

    int  getTotalCount();

    //获取查询用户名后的数据条数

    int  getTotalCountByName(String  name);

    //获取全部用户分页

    List<USER> UserListForPage(int index, int currentCount);


 //根据名称获取用户分页
    List<USER> getbynameforpage(String name,int index, int currentCount);


   //插入用户
    void insert(USER user);

   //通过用户名获取用户
    List<USER>  getbyName(String name);

    //删除用户
    void delete(int id);

    //更新用户
    void  update(USER user);




}