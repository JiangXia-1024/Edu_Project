package com.jiangxia.vodtest;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.*;

import java.util.List;

/**
 * @author jiangxia
 * @date 2022年03月06日 15:31
 */
public class TestVod {
    public static void main(String[] args) {
        String accessKeyId ="LTAI5tKCXnmZnuWBzgKEkuqq";
        String accessKeySecret = "SDWtYU3g0r1RE9tYxJl4mIITgIU0up";
        try {
            TestVod.getPlayAuth(accessKeyId,accessKeySecret);
            TestVod.getPlayUrl(accessKeyId,accessKeySecret);
            uploadVideo(accessKeyId,accessKeySecret,"测试阿里云视频上传","F:\\资料\\阿里云视频上传测试.mp4");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//    1、根据视频id获取视频播放凭证
    public  static void getPlayAuth(String accessKeyId,String accessKeySecret) throws Exception{
        DefaultAcsClient client = InitObject.initVodClient(accessKeyId,accessKeySecret);
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
        request.setVideoId("b81fac36afa24ec987707404b4254587");
        response = client.getAcsResponse(request);
        System.out.println("播放凭证："+response.getPlayAuth());
    }
//    2、根据视频id获取播放地址
    public  static void getPlayUrl(String accessKeyId,String accessKeySecret) throws Exception {
    //创建初始化对象
    DefaultAcsClient client = InitObject.initVodClient(accessKeyId,accessKeySecret);

    //创建获取视频地址request和response
    GetPlayInfoRequest request = new GetPlayInfoRequest();
    GetPlayInfoResponse response = new GetPlayInfoResponse();

    //向request对象里面设置视频id
    request.setVideoId("b81fac36afa24ec987707404b4254587");
    //调用初始化对象里面的方法，传递request，获取数据
    response = client.getAcsResponse(request);

    List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
    //播放地址
    for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
        System.out.print("视频播放地址是：" + playInfo.getPlayURL() + "\n");
    }
    //Base信息
    System.out.print("视频标题是：" + response.getVideoBase().getTitle() + "\n");
    }

//    3、视频上传的方法
    public static void uploadVideo(String accessKeyId,String accessKeySecret,String title,String fileName){
        String videoTitle = "测试阿里云上传视频"; // 上传后的视频名称
        String videopath = "F:\\资料\\阿里云视频上传测试.mp4"; // 本地视频路径和名称
        //上传视频的方法:注意这里的sdk（aliyun-java-vod-upload未开源）所以需要从阿里云官网下载，然后在lib文件夹进入cmd，输入：
        //mvn install:install-file -DgroupId=com.aliyun -DartifactId=aliyun-sdk-vod-upload -Dversion=1.4.11 -Dpackaging=jar -Dfile=aliyun-java-vod-upload-1.4.11.jar
        //即可将aliyun-java-vod-upload引入依赖中
        UploadVideoRequest request = new UploadVideoRequest(accessKeyId, accessKeySecret, title, fileName);
        /* 可指定分片上传时每个分片的大小，默认为2M字节 */
        request.setPartSize(2 * 1024 * 1024L);
        /* 可指定分片上传时的并发线程数，默认为1，(注：该配置会占用服务器CPU资源，需根据服务器情况指定）*/
        request.setTaskNum(1);

        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadVideoResponse response = uploader.uploadVideo(request);

        if (response.isSuccess()) {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
        } else {
            /* 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因 */
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }
    }
}
