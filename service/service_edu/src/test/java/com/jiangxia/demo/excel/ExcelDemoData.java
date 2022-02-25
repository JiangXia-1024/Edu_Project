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
    @ExcelProperty("编号")
    private Integer sno;
    @ExcelProperty("名称")
    private String sname;
}
