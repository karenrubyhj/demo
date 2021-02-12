package com.example.demo.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@lombok.Data
public class USER {


    public int id;

    @NotEmpty(message = "用户名不能为空")
    public String name;

    @NotEmpty(message = "密码不能为空")
    public String password;

    @NotEmpty(message = "性别不能为空")
    public String sex;

    public Integer score;

    public Integer score1;

    public Integer score2;

}
