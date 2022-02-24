package com.jiangxia.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author jiangxia
 * @date 2022年02月19日 22:03
 */
public interface OssService {
    /**
     * 文件上传至阿里云
     * @param file
     * @return
     */
    String upload(MultipartFile file);
}
