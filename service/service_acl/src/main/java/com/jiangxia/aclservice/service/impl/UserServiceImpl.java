package com.jiangxia.aclservice.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiangxia.aclservice.entity.User;
import com.jiangxia.aclservice.mapper.UserMapper;
import com.jiangxia.aclservice.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author:江夏
 * @date 2022/3/26 20:46
 * @version 1.0
 * @description: 用户表 服务实现类
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User selectByUsername(String username) {
        return baseMapper.selectOne(new QueryWrapper<User>().eq("username", username));
    }
}
