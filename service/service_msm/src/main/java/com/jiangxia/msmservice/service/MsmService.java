package com.jiangxia.msmservice.service;

import java.util.Map;

/**
 * @author jiangxia
 * @date 2022年03月14日 12:47
 * desc:发送短信
 */
public interface MsmService {
    //发送短信的方法
    boolean send(Map<String, Object> param, String phone);
}
