package com.jiangxia.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiangxia.eduservice.entity.EduVideo;
import com.jiangxia.eduservice.mapper.EduVideoMapper;
import com.jiangxia.eduservice.service.EduVideoService;
import org.springframework.stereotype.Service;

/**
 * @author jiangxia
 * @date 2022年03月05日 21:24
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    //1 根据课程id删除小节
    // TODO 删除小节，删除对应视频文件
    @Override
    public void removeVideoByCourseId(String courseId) {
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        baseMapper.delete(wrapper);
    }
}

