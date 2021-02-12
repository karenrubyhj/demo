package com.example.demo.httpclient;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.USER;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpGetTest {

    public static String  preurl="http://192.168.0.198";
    public static  int  port=19000;


    // 有参get   使用URI-----------------------------接口自动化工具有用-------------------------------------------

    public String doGetTestWayTwo(String suburl,String p) {

        System.out.println("有参"+suburl+"/n"+p);

        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // url

        URI url=null;

        try {

            // 将参数放入键值对类NameValuePair中,再放入集合中

            List<NameValuePair> params = new ArrayList<>();

            JSONObject json = JSONObject.parseObject(p);

            for (Map.Entry<String, Object> entry : json.entrySet()) {


                params.add(new BasicNameValuePair(entry.getKey().toString(),entry.getValue().toString()));




            }




            //  params.add(new BasicNameValuePair("name", "hj"));

            // params.add(new BasicNameValuePair("age", "18"));

            // 设置uri信息,并将参数集合放入uri;

            // 注:这里也支持一个键值对一个键值对地往里面放setParameter(String key, String value)

            url = new URIBuilder().setScheme("http").setHost(preurl)

                    .setPort(port).setPath(suburl)

                    .setParameters(params).build();


        } catch (URISyntaxException e1) {

            e1.printStackTrace();

        }

        // 创建Get请求

        HttpGet httpGet = new HttpGet(url);

        // 响应模型

        CloseableHttpResponse response = null;

        String str=null;

        try {

            // 配置信息

            RequestConfig requestConfig = RequestConfig.custom()

                    // 设置连接超时时间(单位毫秒)

                    .setConnectTimeout(5000)

                    // 设置请求超时时间(单位毫秒)

                    .setConnectionRequestTimeout(5000)

                    // socket读写超时时间(单位毫秒)

                    .setSocketTimeout(5000)

                    // 设置是否允许重定向(默认为true)

                    .setRedirectsEnabled(true).build();

            // 将上面的配置信息 运用到这个Get请求里

            httpGet.setConfig(requestConfig);

            // 由客户端执行(发送)Get请求

            response = httpClient.execute(httpGet);

            // 从响应模型中获取响应实体


            HttpEntity responseEntity = response.getEntity();


            //  System.out.println("响应状态为:" + response.getStatusLine());

            if (responseEntity != null) {

                // System.out.println("响应内容长度为:" + responseEntity.getContentLength());

                str=EntityUtils.toString(responseEntity);
                System.out.println("响应内容为:" + str);



/*
                List<USER> users=new ArrayList<USER>();

                users= JSON.parseArray(str, USER.class);//json字符串转List<User>

                for (int i = 0; i < users.size(); i++) {

                    USER  user=users.get(i);

                    System.out.println( "用户："+user.getName());

                    System.out.println( "性别："+user.getSex());

                    System.out.println( "语文分数："+user.getScore1());

                }

*/

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

        return  str;

    }



    //无参get方法------------接口自动化工具有用--------------

    public String doGetTestOne(String suburl) {

        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)


        System.out.println("无参"+suburl);


        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // 创建Get请求



        HttpGet httpGet = new HttpGet(preurl+":"+port + suburl);

        // 响应模型

        CloseableHttpResponse response = null;

        String str=null;

        try {

            // 由客户端执行(发送)Get请求


            response = httpClient.execute(httpGet);


            HttpEntity responseEntity = response.getEntity();


            //  System.out.println("响应状态为:" + response.getStatusLine());

            if (responseEntity != null) {

                // System.out.println("响应内容长度为:" + responseEntity.getContentLength());

                str = EntityUtils.toString(responseEntity);
                System.out.println("响应内容为:" + str);

/*
                List<USER> users=new ArrayList<USER>();

                users= JSON.parseArray(str, USER.class);//json字符串转List<User>

                for (int i = 0; i < users.size(); i++) {

                    USER  user=users.get(i);

                    System.out.println( "用户："+user.getName());

                    System.out.println( "性别："+user.getSex());

                    System.out.println( "语文分数："+user.getScore1());

                }
*/


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

        return str;

    }


//---------------------------------------------------------------接口自动化工具没有用-----------------------------------------------------------------

//有参的get请求   GET有参(方式一：直接拼接URL)：

    public void doGetTestWayOne() {


        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)


        CloseableHttpClient httpClient = HttpClientBuilder.create().build();


        // 参数


        StringBuffer params = new StringBuffer();




        try {


            // 字符数据最好encoding以下;这样一来，某些特殊字符才能传过去(如:某人的名字就是“&”,不encoding的话,传不过去)


            params.append("name=" + URLEncoder.encode("hj", "utf-8"));


            //params.append("&");


            //params.append("age=24");


        } catch (UnsupportedEncodingException e1) {



            e1.printStackTrace();


        }


        // 创建Get请求


        HttpGet httpGet = new HttpGet("http://localhost:8080"+"/getAllUser?" + params);


        // 响应模型


        CloseableHttpResponse response = null;


        try {

            // 配置信息


            RequestConfig requestConfig = RequestConfig.custom()

            // 设置连接超时时间(单位毫秒)


                    .setConnectTimeout(5000)

            // 设置请求超时时间(单位毫秒)


                    .setConnectionRequestTimeout(5000)


            // socket读写超时时间(单位毫秒)

                    .setSocketTimeout(5000)


            // 设置是否允许重定向(默认为true)


                    .setRedirectsEnabled(true).build();

            // 将上面的配置信息 运用到这个Get请求里

            httpGet.setConfig(requestConfig);


            // 由客户端执行(发送)Get请求

            response = httpClient.execute(httpGet);

            // 从响应模型中获取响应实体


            HttpEntity responseEntity = response.getEntity();


           // System.out.println("响应状态为:" + response.getStatusLine());

            if (responseEntity != null) {

              //  System.out.println("响应内容长度为:" + responseEntity.getContentLength());


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

       // HttpGetTest test=new HttpGetTest();
           // test.doGetTestOne();
            //test.doGetTestWayOne();
           // test.doGetTestWayTwo();

    }


}
