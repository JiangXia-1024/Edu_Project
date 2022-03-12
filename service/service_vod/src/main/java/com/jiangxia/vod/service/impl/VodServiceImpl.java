package com.jiangxia.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.jiangxia.config.exceptionhandler.CustomizeException;
import com.jiangxia.vod.service.VodService;
import com.jiangxia.vod.util.ConstantVodUtils;
import com.jiangxia.vod.util.InitVodCilent;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jiangxia
 * @date 2022年03月06日 19:40
 */
@Service
public class VodServiceImpl implements VodService {
    @Override
    public String uploadAliyunVideo(MultipartFile file) {
        try{
            //视频标题
            String fileName = file.getOriginalFilename();
            //视频上传之后显示名称
            String title = fileName.substring(0,fileName.lastIndexOf("."));
            InputStream inputStream = file.getInputStream();
            UploadStreamRequest request = new UploadStreamRequest(ConstantVodUtils.ACCESS_KEY_ID,ConstantVodUtils.ACCESS_KEY_SECRET, title, fileName, inputStream);
            UploadVideoImpl uploadVideo = new UploadVideoImpl();
            UploadStreamResponse response = uploadVideo.uploadStream(request);
            String videoid = null;
            if(response.isSuccess()){
                videoid = response.getVideoId();
            }else{
                //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
                videoid = response.getVideoId();
            }
            return videoid;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void removeMoreAlyVideo(List videoIdList) {
        try {
            //初始化对象
            DefaultAcsClient client = InitVodCilent.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            //创建删除视频request对象
            DeleteVideoRequest request = new DeleteVideoRequest();

            //videoIdList值转换成 1,2,3
            String videoIds = StringUtils.join(videoIdList.toArray(), ",");

            //向request设置视频id
            request.setVideoIds(videoIds);
            //调用初始化对象的方法实现删除
            client.getAcsResponse(request);
        }catch(Exception e) {
            e.printStackTrace();
            throw new CustomizeException(20001,"删除视频失败");
        }
    }

    //测试StringUtils.join方法，该方法将list转为string
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("11");
        list.add("22");
        list.add("33");
        // 11,22,33
        String join = StringUtils.join(list.toArray(), ",");
        System.out.println(join);
    }
}
