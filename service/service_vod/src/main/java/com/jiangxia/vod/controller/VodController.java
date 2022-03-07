package com.jiangxia.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.jiangxia.commonutils.ResultData;
import com.jiangxia.config.exceptionhandler.CustomizeException;
import com.jiangxia.vod.service.VodService;
import com.jiangxia.vod.util.ConstantVodUtils;
import com.jiangxia.vod.util.InitVodCilent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author jiangxia
 * @date 2022年03月06日 19:38
 * desc:视频上传相关接口
 */
@RestController
@RequestMapping("/eduvod/video")
@CrossOrigin
public class VodController {
    @Autowired
    private VodService vodService;

    @PostMapping("uploadAlyiVideo")
    public ResultData uploadAlyiVideo(MultipartFile file){
        //返回上传视频的视频id
        String videoId = vodService.uploadAliyunVideo(file);
        return ResultData.ok().data("videoId",videoId);
    }

    //根据视频id删除阿里云视频
    @DeleteMapping("removeAlyVideo/{id}")
    public ResultData removeAlyVideo(@PathVariable String id) {
        try {
            //初始化对象
            DefaultAcsClient client = InitVodCilent.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            //创建删除视频request对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            //向request设置视频id
            request.setVideoIds(id);
            //调用初始化对象的方法实现删除
            client.getAcsResponse(request);
            return ResultData.ok();
        }catch(Exception e) {
            e.printStackTrace();
            throw new CustomizeException(20001,"删除视频失败");
        }
    }

    //删除多个阿里云视频的方法
    //参数多个视频id  List videoIdList
    @DeleteMapping("delete-batch")
    public ResultData deleteBatch(@RequestParam("videoIdList") List<String> videoIdList) {
        vodService.removeMoreAlyVideo(videoIdList);
        return ResultData.ok();
    }
}
