package com.jiangxia.oss.service.impl;

import com.aliyun.oss.OSSClient;
import com.jiangxia.commonutils.ResultCode;
import com.jiangxia.config.exceptionhandler.CustomizeException;
import com.jiangxia.oss.service.OssService;
import com.jiangxia.oss.utils.ConstantPropertiesUtil;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author jiangxia
 * @date 2022年02月19日 22:03
 */
@Service
public class OssServiceImpl implements OssService {
    @Override
    public String upload(MultipartFile file) {
        //先获取阿里云oss存储的相关常量
        String endpoint = ConstantPropertiesUtil.END_POINT;
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;

        String uploadUrl = null;

        try {
            // 创建OSS实例。
            OSSClient ossClient = new OSSClient(endpoint,accessKeyId,accessKeySecret);
            //获取上传文件输入流
            InputStream inputStream = file.getInputStream();
            //获取文件名称
            String fileName = file.getOriginalFilename();

            //1 在文件名称里面添加随机唯一的值
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            // yuy76t5rew01.jpg
            fileName = uuid+fileName;

            //2 把文件按照日期进行分类
            //获取当前日期
            //   2019/11/12
            String datePath = new DateTime().toString("yyyy/MM/dd");
            //拼接
            //  2019/11/12/ewtqr313401.jpg
            fileName = datePath+"/"+fileName;

            //调用oss方法实现上传
            //第一个参数  Bucket名称
            //第二个参数  上传到oss文件路径和文件名称   aa/bb/1.jpg
            //第三个参数  上传文件输入流
            ossClient.putObject(bucketName,fileName , inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();

            //把上传之后文件路径返回
            //需要把上传到阿里云oss路径手动拼接出来
            //  https://edu-guli-1010.oss-cn-beijing.aliyuncs.com/01.jpg
            uploadUrl = "https://"+bucketName+"."+endpoint+"/"+fileName;
            return uploadUrl;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
