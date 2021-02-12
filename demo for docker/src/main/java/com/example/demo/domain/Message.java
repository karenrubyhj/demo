package com.example.demo.domain;


@lombok.Data
public class Message {

    public String  result;
    public String  message;

    public Message(String result,String message) {
        this.message=message;
        this.result=result;
    }


}
