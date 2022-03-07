package com.jiangxia.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author jiangxia
 * @date 2022年03月06日 19:40
 * desc: 传视频到阿里云
 */
public interface VodService {
    String uploadAliyunVideo(MultipartFile file);

    //删除多个阿里云视频的方法
    void removeMoreAlyVideo(List videoIdList);
}
