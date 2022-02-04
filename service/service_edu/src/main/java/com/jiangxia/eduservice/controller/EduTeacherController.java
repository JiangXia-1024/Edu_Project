package com.jiangxia.eduservice.controller;


import com.jiangxia.eduservice.entity.EduTeacher;
import com.jiangxia.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 讲师 前端控制器
 * @author jiangxia
 * @since 2022-02-04
 */
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {

    @Autowired
    EduTeacherService eduTeacherService;

    //rest风格：查询所有的老师的数据
    @GetMapping("findall")
    public List<EduTeacher> findAllTeacher(){
        //调用service方法查询所有数据
        List<EduTeacher> eduTeachers = eduTeacherService.list(null);
        return eduTeachers;
    }
}

