package com.example.demo.httpclient;

import com.sun.rowset.internal.Row;
import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.*;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.biff.WritableWorkbookImpl;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import javax.annotation.processing.FilerException;
import javax.print.DocFlavor;
import java.io.*;
import java.util.ArrayList;

//POI读写接口case的excel文件，又读又写真棒！！！


public class ReadExcel {


    public void readexcel(String  filePath) throws IOException{

        FileInputStream fileInputStream=new FileInputStream(filePath);  //获取d://test.xls,建立数据的输入通道

        POIFSFileSystem poifsFileSystem=new POIFSFileSystem(fileInputStream);  //使用POI提供的方法得到excel的信息

        HSSFWorkbook Workbook=new HSSFWorkbook(poifsFileSystem);//得到文档对象

        HSSFSheet sheet=Workbook.getSheet("sheet1");  //根据name获取sheet表

        FileOutputStream out=new FileOutputStream(filePath);

        String result=null;

        HttpGetTest gettest=new HttpGetTest();

        HttpPostTest posttest=new HttpPostTest();

        for (int i = 1; i <=1; i++) {

            HSSFRow row=sheet.getRow(i);

            HSSFCell cell2 = row.getCell(2);

            HSSFCell cell3 = row.getCell(3);

            HSSFCell cell1 = row.getCell(1);

            if(cell2.getStringCellValue().equals("get")) {

                    System.out.println("get-------------------------------");



                   if (row.getCell(3)==null){



                       result=gettest.doGetTestOne(cell1.getStringCellValue());

                   }else{



                       result=gettest.doGetTestWayTwo(cell1.getStringCellValue(),cell3.getStringCellValue());

                   }



               }else if(cell2.getStringCellValue().equals("post")){


                System.out.println("post-------------------------------");

                        result=posttest.doPostForJson(cell1.getStringCellValue(),cell3.getStringCellValue());





            }

            System.out.println(result);

            row.createCell(4).setCellValue(result);


        }

        out.flush();
        Workbook.write(out);
        out.close();



    }


    public static void main(String  args[]) throws IOException {


                 ReadExcel  rd=new  ReadExcel();


                rd.readexcel("d://jiaoyu.xls");


    }


}