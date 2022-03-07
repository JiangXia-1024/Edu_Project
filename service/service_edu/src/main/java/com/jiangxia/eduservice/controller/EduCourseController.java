package com.jiangxia.eduservice.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.jiangxia.commonutils.ResultData;
import com.jiangxia.eduservice.entity.EduCourse;
import com.jiangxia.eduservice.entity.vo.CourseInfoVo;
import com.jiangxia.eduservice.entity.vo.CoursePublishVo;
import com.jiangxia.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    //课程列表 基本实现
    //TODO  完善条件查询带分页
    @GetMapping
    public ResultData getCourseList() {
        List<EduCourse> list = courseService.list(null);
        return ResultData.ok().data("list",list);
    }
    //添加课程基本信息的方法
    @PostMapping("addCourseInfo")
    public ResultData addCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        //返回添加之后课程id，为了后面添加大纲使用
        String id = courseService.saveCourseInfo(courseInfoVo);
        return ResultData.ok().data("courseId",id);
    }
    //根据课程id查询课程基本信息
    @GetMapping("getCourseInfo/{courseId}")
    public ResultData getCourseInfo(@PathVariable String courseId) {
        CourseInfoVo courseInfoVo = courseService.getCourseInfo(courseId);
        return ResultData.ok().data("courseInfoVo",courseInfoVo);
    }

    //修改课程信息
    @PostMapping("updateCourseInfo")
    public ResultData updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        courseService.updateCourseInfo(courseInfoVo);
        return ResultData.ok();
    }

    //根据课程id查询课程确认信息
    @GetMapping("getPublishCourseInfo/{id}")
    public ResultData getPublishCourseInfo(@PathVariable String id) {
        CoursePublishVo coursePublishVo = courseService.publishCourseInfo(id);
        return ResultData.ok().data("publishCourse",coursePublishVo);
    }

    //课程最终发布
    //修改课程状态
    @PostMapping("publishCourse/{id}")
    public ResultData publishCourse(@PathVariable String id) {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus("Normal");//设置课程发布状态
        courseService.updateById(eduCourse);
        return ResultData.ok();
    }

    //删除课程
    @DeleteMapping("{courseId}")
    public ResultData deleteCourse(@PathVariable String courseId) {
        courseService.removeCourse(courseId);
        return ResultData.ok();
    }
}
