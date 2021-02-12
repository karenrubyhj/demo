package com.example.demo.controller;


import com.example.demo.Util.SHA;

import com.example.demo.domain.USER;
import com.example.demo.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.hbase.thirdparty.com.google.gson.Gson;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import java.io.EOFException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Random;


@RestController    //返回列表数据的

@Api("用户登录")
public class GetUserController {
    @Autowired
    UserService userService;



    @GetMapping(value = "/getbyName")


    public List<USER> getbyname(String name) {


        return userService.getbyName(name);


    }





    @GetMapping(value = "/isexist1")

    public boolean nameisexist1(String name) {



        if( userService.getbyName(name).size()==0)
        {
            return false;

        }

        else return true;

    }




    //登录2
    @RequestMapping("/login2")
    public String login2(HttpServletRequest request) {

        String data=request.getParameter("data");

        // String data="{\"name\": \"yuanbo\",\"password\": \"123456\"}";

        System.out.println("data is :"+data);

        Gson gson = new Gson();
        USER user = gson.fromJson(data, USER.class);

        String nametemp=user.getName();
        String  pwtemp=user.getPassword();

        if(nameisexist1(nametemp)==false)

        { return  "用户不存在";}
        else if(!SHA.getSHA256StrJava(pwtemp).equals(userService.getbyName(nametemp).get(0).getPassword()))

        {  return  "密码错误";}

        else



            return "登录成功";


    }






//注册

    @RequestMapping(value="/register",method=RequestMethod.POST)
    public String register(@ModelAttribute("user") USER user) {


        System.out.println("username is:"+user.getName());
        System.out.println("password is:"+user.getPassword());
        System.out.println("password is:"+user.getSex());

        if(nameisexist1(user.getName())==true)

        { return  "用户名已存在";}

        USER temp = new USER();

        temp.setName(user.getName());
        temp.setPassword(SHA.getSHA256StrJava(user.getPassword()));
        temp.setSex(user.getSex());

        try {
            userService.insert(temp);

            return "注册成功";

        }catch (Exception e){

            return "注册失败，请联系管理员";

        }
    }









}