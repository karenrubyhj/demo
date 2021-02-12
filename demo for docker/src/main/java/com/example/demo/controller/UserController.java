package com.example.demo.controller;

import com.example.demo.Util.SHA;
import com.example.demo.dao.UserDao;
import com.example.demo.domain.PageBean;
import com.example.demo.domain.USER;
import com.example.demo.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;


@Controller
public class UserController {
    @Autowired
    UserService userService;



    @ResponseBody
    @GetMapping(value = "/getAllUser")

    public List<USER> getAllUser(String name) {

        if (name.equals(""))

            return userService.getAllUser();


        else

            return userService.getbyName(name);
    }


    @ResponseBody
    @GetMapping(value = "/getUserforPage")

    public PageBean getUserforPage(@ModelAttribute("pageBean") PageBean pageBean, Model model,String  name,int currentPage, int currentCount) {

        //将请求传递到dao层，从数据库获取所有的商品信息



        //模拟当前是第一页
      //  int currentPage = 2;
        //认为每页显示10条
      //  int currentCount = 10;


        try {


            pageBean = userService.findPageBean(name,currentPage,currentCount);

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return  pageBean;



    }



    /*  正确的get方法
    @GetMapping(value = "/insert")
    public void insert(@Param("name") String name,
                       @Param("password") String password) {

        USER user = new USER();

        System.out.println(name);
        System.out.println(password);
        user.setName(name);
        user.setPassword(password);

        userService.insert(user);
    }

 */

/*  错误的post方法
 @PostMapping(value = "/insert")
  public void insert(@RequestBody  USER param) {

    USER user = new USER();

    System.out.println(param.getName());
    System.out.println(param.getPassword());
    user.setName(param.getName());
    user.setPassword(param.getPassword());

    userService.insert(user);
}
*/


//正确的post方法1


    @RequestMapping(value = "/insert", method = RequestMethod.POST, produces = {"application/json; charset=UTF-8"})
    public String insert(@ModelAttribute("user") USER user) {


        System.out.println("username is:" + user.getName());
        System.out.println("password is:" + user.getPassword());
        System.out.println("password is:" + user.getSex());
        System.out.println("score is:" + user.getScore());
        System.out.println("score is:" + user.getScore1());
        System.out.println("score is:" + user.getScore2());

        USER temp = new USER();


        temp.setName(user.getName());
        temp.setPassword(SHA.getSHA256StrJava(user.getPassword()));
        temp.setSex(user.getSex());

        String s1=user.getScore()+"";
        if("".equals(s1)){

            temp.setScore(null);
        }else{

            temp.setScore(user.getScore());

        }

        String s2=user.getScore()+"";
        if("".equals(s2)){

            temp.setScore1(null);
        }else{

            temp.setScore1(user.getScore1());

        }


        String s3=user.getScore()+"";
        if("".equals(s3)){

            temp.setScore2(null);
        }else{

            temp.setScore2(user.getScore2());

        }


        userService.insert(temp);


      //  return "redirect:/CRUD.html";
        return "success";


    }




/*正确的post方法2

    @RequestMapping("/insert")
    public String insert(HttpServletRequest request) {
        String name=request.getParameter("name");
        String password=request.getParameter("password");
        System.out.println("name is:"+name);
        System.out.println("password is:"+password);
        return name+password;
    }

*/


    @ResponseBody
    @GetMapping(value = "/delete")

    public boolean delete(@Param("id") int id) {

        System.out.println("delete");

        try {

            userService.delete(id);
            return true;

        } catch (Exception e) {


            return false;
        }


    }


    @ResponseBody
    @GetMapping(value = "/deleteall")

    public boolean delete(String[] ids) {

        //   System.out.println(ids);

        //   String  items[]=ids.split(",");

        try {

            for (int i = 0; i < ids.length; i++) {


                userService.delete(Integer.parseInt(ids[i]));


            }
            return true;


        } catch (Exception e) {


            return false;
        }
    }


    @ResponseBody
    @GetMapping(value = "/update")
    public boolean update( @Param("id") String id, @Valid @Param("name") String name,@Valid  @Param("sex") String sex, @Param("score") String score) {

        try {

            USER user = new USER();

            System.out.println(name);
            System.out.println(sex);
            System.out.println(score);

            user.setId(Integer.parseInt(id));
            user.setName(name);
            user.setSex(sex);

            if(score.equals(""))
                user.setScore(null);
            else
                user.setScore(Integer.parseInt(score));

            userService.update(user);

            return  true;

        }catch (Exception e){


            return false;
        }




    }









}