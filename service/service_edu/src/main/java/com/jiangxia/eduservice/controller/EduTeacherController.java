package com.jiangxia.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiangxia.commonutils.ResultCode;
import com.jiangxia.commonutils.ResultData;
import com.jiangxia.eduservice.entity.EduTeacher;
import com.jiangxia.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 讲师 前端控制器
 * @author jiangxia
 * @since 2022-02-04
 */
@Api(description="讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {

    @Autowired
    EduTeacherService eduTeacherService;

    //1、查询所有的老师的数据
    @ApiOperation(value = "所以讲师列表")
    @GetMapping("findall")//rest风格
    public ResultData findAllTeacher(){
        //调用service方法查询所有数据
        List<EduTeacher> eduTeachers = eduTeacherService.list(null);
        //链式编程
        return ResultData.ok().data("items",eduTeachers);
    }

    //2、根据id逻辑删除老师
    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("{id}")
    public ResultData removeTeacher(@ApiParam(name="id",value = "讲师ID",required = true) @PathVariable String id){
        boolean flag = eduTeacherService.removeById(id);
        if(flag){
            return ResultData.ok();
        }else{
            return ResultData.error();
        }
    }

    @ApiOperation(value = "分页讲师列表")
    @GetMapping("{page}/{limit}")
    public ResultData pageList(
        @ApiParam(name = "page", value = "当前页码", required = true)
        @PathVariable Long page,
        @ApiParam(name = "limit", value = "每页记录数", required = true)
        @PathVariable Long limit){
        Page<EduTeacher> pageParam = new Page<>(page, limit);
        eduTeacherService.page(pageParam, null);
        List<EduTeacher> records = pageParam.getRecords();
        long total = pageParam.getTotal();
        return  ResultData.ok().data("total", total).data("rows", records);
    }
}

