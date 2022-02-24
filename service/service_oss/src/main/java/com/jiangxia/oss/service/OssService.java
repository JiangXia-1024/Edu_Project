package com.jiangxia.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author jiangxia
 * @date 2022年02月24日 21:52
 */
public interface OssService {
    //上传头像到oss
    String uploadFileAvatar(MultipartFile file);
}
