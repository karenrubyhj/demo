package com.example.demo.controller;



import com.example.demo.domain.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

@RestController

public class Upload1 {
// 返回json串，而不是页面，需要用@RestController标签


    @RequestMapping(value="/upload1")

    @ResponseBody
    public Message upload1(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ModelAndView mv = new ModelAndView();

        System.out.println("dsaf");
        //创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //判断request是否有文件需要上传
        if(multipartResolver.isMultipart(request)){
            //转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;

            List<MultipartFile> fileList = multiRequest.getFiles("file");
            for (MultipartFile mf : fileList) {
                if(!mf.isEmpty()){
                    //取得当前上传文件的名称
                    String myFileName = mf.getOriginalFilename();
                    //如果名称不为""，说明该文件存在，否则说明文件不存在。
                    if(myFileName.trim()!=""){
                        System.out.println(myFileName);
                        //重命名上传后的文件
                        String filename = "demoupload"+mf.getOriginalFilename();
                        //定义上传路劲
                        String path = "D:/"+filename;
                        File localFile = new File(path);
                        mf.transferTo(localFile);
                    }
                }
            }


            return new Message("1","success");

        }else{

            return new Message("2","fail");
        }
    }






}
