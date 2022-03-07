package com.jiangxia.eduservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiangxia.eduservice.entity.EduCourse;
import com.jiangxia.eduservice.entity.vo.CoursePublishVo;

/**
 * @author jiangxia
 * @date 2022年03月02日 20:55
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    public CoursePublishVo getPublishCourseInfo(String courseId);
}
