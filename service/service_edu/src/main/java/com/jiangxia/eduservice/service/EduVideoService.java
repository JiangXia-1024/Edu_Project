package com.jiangxia.eduservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiangxia.eduservice.entity.EduVideo;

public interface EduVideoService extends IService<EduVideo> {
    //1 根据课程id删除小节
    void removeVideoByCourseId(String courseId);
}
