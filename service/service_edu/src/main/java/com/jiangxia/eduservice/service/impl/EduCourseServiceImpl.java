package com.jiangxia.eduservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiangxia.config.exceptionhandler.CustomizeException;
import com.jiangxia.eduservice.entity.EduCourse;
import com.jiangxia.eduservice.entity.EduCourseDescription;
import com.jiangxia.eduservice.entity.vo.CourseInfoVo;
import com.jiangxia.eduservice.mapper.EduCourseMapper;
import com.jiangxia.eduservice.service.EduCourseDescriptionService;
import com.jiangxia.eduservice.service.EduCourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jiangxia
 * @date 2022年03月02日 20:55
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    //课程描述注入
    @Autowired
    private EduCourseDescriptionService courseDescriptionService;

    //添加课程基本信息的方法
    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        //1 向课程表添加课程基本信息
        //CourseInfoVo对象转换eduCourse对象
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if(insert == 0) {
            //添加失败
            throw new CustomizeException(20001,"添加课程信息失败");
        }

        //获取添加之后课程id
        String cid = eduCourse.getId();

        //2 向课程简介表添加课程简介
        //edu_course_description
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setDescription(courseInfoVo.getDescription());
        //设置描述id就是课程id
        courseDescription.setId(cid);
        courseDescriptionService.save(courseDescription);

        return cid;
    }
}
