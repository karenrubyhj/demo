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
import javax.validation.Valid;
import java.io.EOFException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Random;


@Controller   //返回页面

@Api("用户登录")
public class LoginController {
    @Autowired
    UserService userService;





  //查询用户

    @PostMapping(value = "/nameList")
    @ApiOperation(value="查询用户2", notes="根据name来查询用户")
    @ApiImplicitParam(name = "name", value = "用户名")

    public String getalluser(String name,Model model) {


        List<USER> list= userService.getbyName(name);

        model.addAttribute("dtList",list);

        return "redirect:/listtest.html";


    }

    //判断是否存在该用户

    @GetMapping(value = "/isexist")

    public boolean nameisexist(String name) {



           if( userService.getbyName(name).size()==0)
           {
                   return false;

           }

           else return true;

    }




    //登录1


    @RequestMapping(value="/login",method=RequestMethod.POST)
    public String login(@ModelAttribute("user") USER user,Model model) {

       // ModelAndView modelAndView = new ModelAndView();

        String nametemp=user.getName();
        String  pwtemp=user.getPassword();

        System.out.println("用户名"+nametemp);
        System.out.println("密码"+pwtemp);

        if(nameisexist(nametemp)==false) {

           return"noname";
        }
        else if(!SHA.getSHA256StrJava(pwtemp).equals(userService.getbyName(nametemp).get(0).getPassword()))
       {
           return "wrongpswd";

        }else {



          /*  modelAndView.setViewName("index");
            modelAndView.addObject("name","nametemp");

            return modelAndView;*/


          model.addAttribute("name",nametemp);

           return "CRUD";

         //   return "listtest";

            //return "redirect:/CRUD.html";



        }

    }

  //用于跳转到template目录下的页面

    @GetMapping(value = "/crudupdate")

    public String  crudupdate() {


       return  "CRUD";

    }







}