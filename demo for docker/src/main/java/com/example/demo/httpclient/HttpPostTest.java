package com.example.demo.httpclient;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.USER;
import org.apache.catalina.User;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class HttpPostTest {


    public static String  preurl="http://192.168.0.198:19000";



    //----------------------------------------------------接口自动化工具有用--------------------------------------------------------

    public  String doPostForJson(String url, String jsonParams) {

        System.out.println("传入"+url+"/n"+jsonParams);

        CloseableHttpClient httpClient = HttpClients.createDefault();
        JSONObject jsonObject = null;
        HttpPost httpPost = new HttpPost(preurl+url);
        RequestConfig requestConfig = RequestConfig.custom().
                setConnectTimeout(180 * 1000).setConnectionRequestTimeout(180 * 1000)
                .setSocketTimeout(180 * 1000).setRedirectsEnabled(true).build();
        httpPost.setConfig(requestConfig);
        httpPost.setHeader("Content-Type", "application/json");

        httpPost.setHeader("token", "eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJ1c2VybmFtZSI6InpoYW5nc2FuIiwiaWF0IjoxNTg5NDQ4ODc0LCJleHAiOjE2MDE1NDQ4NzR9.SO_XGhL1VU7kkmynPOGBp3IBBS3zVOtcACqdp_nG8F0");

        String result=null;

        try {
            httpPost.setEntity(new StringEntity(jsonParams, ContentType.create("application/json", "utf-8")));
            System.out.println("request parameters" + EntityUtils.toString(httpPost.getEntity()));
            System.out.println("httpPost:" + httpPost);
            HttpResponse response = httpClient.execute(httpPost);
            if (response != null && response.getStatusLine().getStatusCode() == 200) {
                result = EntityUtils.toString(response.getEntity());
                System.out.println("result:" + result);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return result;
        }
    }















    //普通参数--------------------------------------------接口自动化工具没有用--------------------------------------------

    public void doPostTestFour(String suburl,String p) {


        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)


        CloseableHttpClient httpClient = HttpClientBuilder.create().build();



        // 参数

        StringBuffer params = new StringBuffer();

        try {


            // 字符数据最好encoding以下;这样一来，某些特殊字符才能传过去(如:某人的名字就是“&”,不encoding的话,传不过去)



            params.append("name=" + URLEncoder.encode("1", "utf-8"));


            params.append("&");


            params.append("password=1");


        } catch (UnsupportedEncodingException e1) {


            e1.printStackTrace();

        }


        // 创建Post请求

        HttpPost httpPost = new HttpPost("http://localhost:8080"+suburl + "?" + params);



        // 设置ContentType(注:如果只是传普通参数的话,ContentType不一定非要用application/json)


        httpPost.setHeader("Content-Type", "application/json;charset=utf8");


        // 响应模型

        CloseableHttpResponse response = null;


        try {


            // 由客户端执行(发送)Post请求


            response = httpClient.execute(httpPost);


            // 从响应模型中获取响应实体


            HttpEntity responseEntity = response.getEntity();


            System.out.println("响应状态为:" + response.getStatusLine());

            if (responseEntity != null) {


                System.out.println("响应内容长度为:" + responseEntity.getContentLength());


                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));

            }

        } catch (ClientProtocolException e) {


            e.printStackTrace();


        } catch (ParseException e) {


            e.printStackTrace();

        } catch (IOException e) {


            e.printStackTrace();


        } finally {



            try {

                // 释放资源

                if (httpClient != null) {

                    httpClient.close();

                }

                if (response != null) {

                    response.close();

                }

            } catch (IOException e) {

                e.printStackTrace();

            }

        }

    }




    public static void main(String[] args)
    {

        HttpPostTest test=new HttpPostTest();


    }

}
