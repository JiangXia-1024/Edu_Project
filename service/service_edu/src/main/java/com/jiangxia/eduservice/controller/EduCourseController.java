package com.jiangxia.eduservice.controller;

import com.jiangxia.commonutils.ResultData;
import com.jiangxia.eduservice.entity.vo.CourseInfoVo;
import com.jiangxia.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author jiangxia
 * @date 2022年03月02日 20:30
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {
    @Autowired
    private EduCourseService courseService;

    //添加课程基本信息的方法
    @PostMapping("addCourseInfo")
    public ResultData addCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        //返回添加之后课程id，为了后面添加大纲使用
        String id = courseService.saveCourseInfo(courseInfoVo);
        return ResultData.ok().data("courseId",id);
    }
}
