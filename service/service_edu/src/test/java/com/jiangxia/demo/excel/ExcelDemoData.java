package com.jiangxia.demo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author jiangxia
 * @date 2022年02月25日 21:35
 * desc:测试easyexcel
 */
@Data
public class ExcelDemoData {

    //    设置excelproperty
    //设置列对应的属性
    @ExcelProperty(index = 0)
//    @ExcelProperty("编号")
    private Integer sno;
//    @ExcelProperty("名称")
    //设置列对应的属性
    @ExcelProperty(index = 1)
    private String sname;

}
