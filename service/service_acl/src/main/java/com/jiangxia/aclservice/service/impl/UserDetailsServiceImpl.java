package com.jiangxia.aclservice.service.impl;


import com.jiangxia.aclservice.entity.User;
import com.jiangxia.aclservice.service.PermissionService;
import com.jiangxia.aclservice.service.UserService;
import com.jiangxia.security.entity.SecurityUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author:江夏
 * @date 2022/3/26 20:45
 * @version 1.0
 * @description: 自定义userDetailsService - 认证用户详情
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    /***
     * 根据账号获取用户信息
     * @param username:
     * @return: org.springframework.security.core.userdetails.UserDetails
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 从数据库中取出用户信息
        User user = userService.selectByUsername(username);

        // 判断用户是否存在
        if (null == user){
            //throw new UsernameNotFoundException("用户名不存在！");
        }
        // 返回UserDetails实现类
        com.jiangxia.security.entity.User curUser = new com.jiangxia.security.entity.User();
        BeanUtils.copyProperties(user,curUser);

        List<String> authorities = permissionService.selectPermissionValueByUserId(user.getId());
        SecurityUser securityUser = new SecurityUser(curUser);
        securityUser.setPermissionValueList(authorities);
        return securityUser;
    }

}