package com.jiangxia.aclservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiangxia.aclservice.entity.User;

/**
 * @author:江夏
 * @date 2022/3/26 20:47
 * @version 1.0
 * @description: 用户表 服务类
 */
public interface UserService extends IService<User> {

    // 从数据库中取出用户信息
    User selectByUsername(String username);
}
