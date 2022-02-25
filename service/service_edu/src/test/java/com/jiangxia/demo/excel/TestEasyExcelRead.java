package com.jiangxia.demo.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author jiangxia
 * @date 2022年02月25日 23:20
 */
public class TestEasyExcelRead {
    public static void main(String[] args) throws FileNotFoundException {
        // 写法1：
        String fileName = "F:\\easyExcelDemo.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, ExcelDemoData.class, new ExcelListener()).sheet().doRead();
        // 写法2：
//        InputStream in = new BufferedInputStream(new FileInputStream("F:\\01.xlsx"));
//        ExcelReader excelReader = EasyExcel.read(in, ExcelDemoData.class, new ExcelListener()).build();
//        ReadSheet readSheet = EasyExcel.readSheet(0).build();
//        excelReader.read(readSheet);
//        // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
//        excelReader.finish();
    }
}
