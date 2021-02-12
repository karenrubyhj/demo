package com.example.demo.domain;

import com.example.demo.domain.USER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@lombok.Data
public class PageBean {

      public  int currentPage;
      public int currentCount;
      public int totalCount;
      public int  totalPage;
      public List<USER> userlist;



}
