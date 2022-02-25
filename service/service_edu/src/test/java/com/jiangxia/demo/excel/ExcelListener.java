package com.jiangxia.demo.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author jiangxia
 * @date 2022年02月25日 23:17
 */
public class ExcelListener extends AnalysisEventListener<ExcelDemoData> {

    //创建list集合封装最终的数据
    List<ExcelDemoData> list = new ArrayList<ExcelDemoData>();

    //一行一行去读取excle内容
    @Override
    public void invoke(ExcelDemoData excelDemoData, AnalysisContext analysisContext) {
        System.out.println("***"+excelDemoData);
        list.add(excelDemoData);
    }

    //读取excel表头信息
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头信息："+headMap);
    }

    //读取完成后执行
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
