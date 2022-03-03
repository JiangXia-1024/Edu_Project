package com.jiangxia.eduservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiangxia.eduservice.entity.EduSubject;
import com.jiangxia.eduservice.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author jiangxia
 * @date 2022年02月27日 19:52
 * desc:课程分类service
 */
public interface EduSubjectService extends IService<EduSubject> {

    //添加课程分类
    void saveSubject(MultipartFile file,EduSubjectService subjectService);
    //课程分类列表（树形）
    List<OneSubject> getAllOneTwoSubject();
}
