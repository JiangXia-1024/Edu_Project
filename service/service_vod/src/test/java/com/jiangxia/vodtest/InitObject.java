package com.jiangxia.vodtest;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;

/**
 * @author jiangxia
 * @date 2022年03月06日 15:27
 */
public class InitObject {
    public static DefaultAcsClient initVodClient(String accessKeyId,String accessKeySecret){
        String regionid = "cn-shanghai";//点播服务接入区域
        DefaultProfile defaultProfile = DefaultProfile.getProfile(regionid,accessKeyId,accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(defaultProfile);
        return client;
    }
}
