package com.jiangxia.eduservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiangxia.eduservice.entity.EduCourse;
import com.jiangxia.eduservice.entity.frontvo.CourseWebVo;
import com.jiangxia.eduservice.entity.vo.CoursePublishVo;

/**
 * @author jiangxia
 * @date 2022年03月02日 20:55
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    public CoursePublishVo getPublishCourseInfo(String courseId);

    //根据课程id，编写sql语句查询课程信息
    CourseWebVo getBaseCourseInfo(String courseId);
}
