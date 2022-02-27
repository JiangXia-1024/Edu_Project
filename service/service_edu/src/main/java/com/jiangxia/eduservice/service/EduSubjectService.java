package com.jiangxia.eduservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiangxia.eduservice.entity.EduSubject;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author jiangxia
 * @date 2022年02月27日 19:52
 * desc:课程分类service
 */
public interface EduSubjectService extends IService<EduSubject> {

    //添加课程分类
    void saveSubject(MultipartFile file,EduSubjectService subjectService);
}
