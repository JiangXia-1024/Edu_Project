package com.jiangxia.eduservice.controller;

import com.jiangxia.commonutils.ResultData;
import com.jiangxia.eduservice.entity.subject.OneSubject;
import com.jiangxia.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author jiangxia
 * @date 2022年02月27日 19:49
 * desc:课程分类前端控制器
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {
    @Autowired
    private EduSubjectService eduSubjectService;

    @PostMapping("addSubject")
    public ResultData addSubject(MultipartFile file){
        //上传过来的excel文件
        eduSubjectService.saveSubject(file,eduSubjectService);
        return ResultData.ok();
    }
    //课程分类列表（树形）
    @GetMapping("getAllSubject")
    public ResultData getAllSubject() {
        //list集合泛型是一级分类
        List<OneSubject> list = eduSubjectService.getAllOneTwoSubject();
        return ResultData.ok().data("list",list);
    }
}
