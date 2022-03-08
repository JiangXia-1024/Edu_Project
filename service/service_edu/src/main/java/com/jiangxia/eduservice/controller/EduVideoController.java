package com.jiangxia.eduservice.controller;

import com.jiangxia.commonutils.ResultData;
import com.jiangxia.eduservice.client.VodClient;
import com.jiangxia.eduservice.entity.EduVideo;
import com.jiangxia.eduservice.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.StringUtils;

/**
 * @author jiangxia
 * @date 2022年03月02日 20:30
 * desc:课程视频
 */
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public class EduVideoController {
    @Autowired
    private EduVideoService videoService;
    //注入vodclient
    @Autowired
    VodClient vodClient;

    //添加小节
    @PostMapping("addVideo")
    public ResultData addVideo(@RequestBody EduVideo eduVideo) {
        videoService.save(eduVideo);
        return ResultData.ok();
    }

    //删除小节
    // TODO 后面这个方法需要完善：删除小节时候，同时把里面视频删除
    @DeleteMapping("{id}")
    public ResultData deleteVideo(@PathVariable String id) {
        //先查询视频id
        EduVideo eduVideo = videoService.getById(id);
        String sourceid = eduVideo.getVideoSourceId();
        //如果视频id不为空，则删除
        if(!StringUtils.isEmpty(sourceid)){
            vodClient.removeVideo(sourceid);
        }
        videoService.removeById(id);
        return ResultData.ok();
    }

    //修改小节 TODO
}
