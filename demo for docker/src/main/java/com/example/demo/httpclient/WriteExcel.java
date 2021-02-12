package com.example.demo.httpclient;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;


//JXL 往已有的excel写数据简直就是垃圾垃圾垃圾


public class WriteExcel {


    public  void  writeexcel(ArrayList<Url> urls) throws IOException{

        File xlsFile = new File("d://ApiTest.xls");
        WritableWorkbook workbook = Workbook.createWorkbook(xlsFile);
        WritableSheet sheet = workbook.createSheet("sheet1", 0);

        int  row=1;


        try {

                  for(int i=0;i<urls.size();i++) {

                      sheet.addCell(new Label(0, row, urls.get(i).getSummary()));

                      sheet.addCell(new Label(1, row, urls.get(i).getSuburl()));
                      sheet.addCell(new Label(2, row, urls.get(i).getMethod()));

                      sheet.addCell(new Label(3, row, urls.get(i).getParam()));

                      row++;



                  }
                workbook.write();
                workbook.close();

        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
    }



}





