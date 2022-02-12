package com.jiangxia.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiangxia.commonutils.ResultCode;
import com.jiangxia.commonutils.ResultData;
import com.jiangxia.eduservice.entity.EduTeacher;
import com.jiangxia.eduservice.entity.vo.TeacherQuery;
import com.jiangxia.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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

//    条件查询带分页的方法
//    @GetMapping("pageTeacherCondition/{current}/{limit}")
//    public ResultData pageTeacherCondition1(@PathVariable long current, @PathVariable long limit, TeacherQuery teacherQuery){
//        //创建page对象
//        Page<EduTeacher> page = new Page<>(current,limit);
//        //构建条件
//        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
//        //多条件组合查询
//        //判断条件值是否为空，如果不为空则拼接条件
//        String name = teacherQuery.getName();
//        Integer level = teacherQuery.getLevel();
//        String begin = teacherQuery.getBegin();
//        String end = teacherQuery.getEnd();
//        if(!StringUtils.isEmpty(name)) {
//            //构建条件
//            wrapper.like("name",name);
//        }
//        if(!StringUtils.isEmpty(level)) {
//            wrapper.eq("level",level);
//        }
//        if(!StringUtils.isEmpty(begin)) {
//            wrapper.ge("gmt_create",begin);
//        }
//        if(!StringUtils.isEmpty(end)) {
//            wrapper.le("gmt_create",end);
//        }
//
//        //调用方法实现条件分页查询
//        eduTeacherService.page(page,wrapper);
//        long total = page.getTotal();//总记录数
//        List<EduTeacher> records = page.getRecords();//数据list集合
//        return ResultData.ok().data("total",total).data("rows",records);//数据结果集
//    }
    //条件查询带分页的方法使用RequestBody注解
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public ResultData pageTeacherCondition(@PathVariable long current, @PathVariable long limit, @RequestBody(required = false) TeacherQuery teacherQuery){
        //创建page对象
        Page<EduTeacher> page = new Page<>(current,limit);
        //构建条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        //多条件组合查询
        //判断条件值是否为空，如果不为空则拼接条件
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        if(!StringUtils.isEmpty(name)) {
            //构建条件
            wrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(level)) {
            wrapper.eq("level",level);
        }
        if(!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create",end);
        }

        //调用方法实现条件分页查询
        eduTeacherService.page(page,wrapper);
        long total = page.getTotal();//总记录数
        List<EduTeacher> records = page.getRecords();//数据list集合
        return ResultData.ok().data("total",total).data("rows",records);//数据结果集
    }

    //添加讲师接口
    @PostMapping("addTeacher")
    public ResultData addTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean save = eduTeacherService.save(eduTeacher);
        if(save) {
            return ResultData.ok();
        } else {
            return ResultData.error();
        }
    }

    //根据讲师id进行查询
    @GetMapping("getTeacher/{id}")
    public ResultData getTeacher(@PathVariable String id) {
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        return ResultData.ok().data("teacher",eduTeacher);
    }

    //讲师修改功能
    @PostMapping("updateTeacher")
    public ResultData updateTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean flag = eduTeacherService.updateById(eduTeacher);
        if(flag) {
            return ResultData.ok();
        } else {
            return ResultData.error();
        }
    }

}

