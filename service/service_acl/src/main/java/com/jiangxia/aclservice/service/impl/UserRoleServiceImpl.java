package com.jiangxia.aclservice.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiangxia.aclservice.entity.UserRole;
import com.jiangxia.aclservice.mapper.UserRoleMapper;
import com.jiangxia.aclservice.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * @author:江夏
 * @date 2022/3/26 20:45
 * @version 1.0
 * @description: 服务实现类
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
