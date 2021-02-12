package com.example.demo.httpclient;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Automation {



    public static String getReturnData(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
        urlConn.setDoInput(true);        //设置输入流采用字节流
        urlConn.setDoOutput(true);        //设置输出流采用字节流
        urlConn.setRequestMethod("GET");
        urlConn.setUseCaches(false);    //设置缓存
        urlConn.setRequestProperty("Content-Type", "application/json");
        urlConn.setRequestProperty("Charset", "utf-8");

        urlConn.connect();
        StringBuffer sbuffer=null;
        if (urlConn.getResponseCode()==200) {
            // 从服务器获得一个输入流
            InputStreamReader   inputStream =new InputStreamReader(urlConn.getInputStream());//调用HttpURLConnection连接对象的getInputStream()函数, 将内存缓冲区中封装好的完整的HTTP请求电文发送到服务端。
            BufferedReader reader = new BufferedReader(inputStream);
            String lines;
            sbuffer= new StringBuffer("");
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), "utf-8");
                sbuffer.append(lines);
            }
            reader.close();
        }else{

        }
        //断开连接
        urlConn.disconnect();

        return sbuffer.toString();


    }



    public static void main(String args[]) throws IOException{

        WriteExcel we=new WriteExcel();

        Automation  test=new Automation();

        ArrayList urls=new ArrayList<Url>();


        String queryUrl = "http://192.168.0.198:19000/edu-admin/v2/api-docs";
        String swagger = test.getReturnData(queryUrl); //请求接口数据
       // System.out.println(swagger);


        JSONObject json = JSONObject.parseObject(swagger);

        String  url=json.get("host").toString();

        String  basepath=json.get("basePath").toString();

        JSONObject paths=(JSONObject) json.get("paths");

       // System.out.println(paths.toString());

        JSONObject  temp=null;
        for (Map.Entry<String, Object> entry : paths.entrySet()) {


            //System.out.println("keys:"+entry.getKey());
           // System.out.println("values:"+entry.getValue());

            Url urlobject=new Url();

            urlobject.setSuburl(entry.getKey());

            JSONObject value=JSONObject.parseObject(entry.getValue().toString());



            if(value.containsKey("post")) {

                urlobject.setMethod("post");

                temp=JSONObject.parseObject(value.get("post").toString());



            }
            else if(value.containsKey("get")) {
                urlobject.setMethod("get");

                temp=JSONObject.parseObject(value.get("get").toString());



            }

            else if(value.containsKey("delete")) {
                urlobject.setMethod("delete");

                temp=JSONObject.parseObject(value.get("delete").toString());



            }
            else if(value.containsKey("put")) {
                urlobject.setMethod("put");

                temp=JSONObject.parseObject(value.get("put").toString());



            }





            System.out.println(temp);

            if(temp.containsKey("summary")) {
                urlobject.setSummary(temp.get("summary").toString());
              //  System.out.println(urlobject.getSummary());
            }
            else{

                urlobject.setSummary("");

            }


            JSONArray params=JSONArray.parseArray(temp.get("parameters").toString());

            JSONObject param1=(JSONObject)params.get(0);

            if(param1.containsKey("schema")) {

                JSONObject schema = (JSONObject) param1.get("schema");


              //  System.out.println(schema);

               if(schema.containsKey("originalRef")){
                    String paramurl = schema.get("originalRef").toString();

                 //   System.out.println(paramurl);


                    JSONObject definitions = (JSONObject) json.get("definitions");


                    JSONObject definition = (JSONObject) definitions.get(paramurl);


                    urlobject.setParam(definition.get("properties").toString());
                }
                else{


                    urlobject.setParam(param1.get("name").toString());

                }


            }
            else{

                urlobject.setParam(param1.get("name").toString());

            }



           urls.add(urlobject);


        }


        System.out.println(urls.toString());






           we.writeexcel(urls);










    }












}
