package com.jiangxia.demo.excel;

import com.alibaba.excel.EasyExcel;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jiangxia
 * @date 2022年02月25日 21:38
 */
public class TestEasyExcel {
    public static void main(String[] args) {
        //实现excel写操作
//        写法一：会自动关流
//        1、设置写入文件的文件夹地址和excel名称
        String fileName = "F:\\easyExcelDemo.xlsx";
//        2、调用easyexcel里面的方法实现写的操作
//                write方法有两个参数：1、文件路径；2、实体类class
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName,ExcelDemoData.class).sheet("测试列表").doWrite(getData());


        // 写法2，方法二需要手动关闭流
//        String fileName = "F:\\112.xlsx";
//        // 这里 需要指定写用哪个class去写
//        ExcelWriter excelWriter = EasyExcel.write(fileName, DemoData.class).build();
//        WriteSheet writeSheet = EasyExcel.writerSheet("写入方法二").build();
//        excelWriter.write(data(), writeSheet);
//        /// 千万别忘记finish 会帮忙关闭流
//        excelWriter.finish();

    }

    private static List<ExcelDemoData> getData() {
        List<ExcelDemoData> list = new ArrayList<ExcelDemoData>();
        for (int i = 0; i < 10; i++) {
            ExcelDemoData data = new ExcelDemoData();
            data.setSno(i);
            data.setSname("亚索"+i);
            list.add(data);
        }
        return list;
    }
}
