package com.jiangxia.aclservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiangxia.aclservice.entity.Role;

import java.util.List;
import java.util.Map;
/**
 * @author:江夏
 * @date 2022/3/26 20:46
 * @version 1.0
 * @description: 服务类
 */
public interface RoleService extends IService<Role> {

    //根据用户获取角色数据
    Map<String, Object> findRoleByUserId(String userId);

    //根据用户分配角色
    void saveUserRoleRealtionShip(String userId, String[] roleId);

    List<Role> selectRoleByUserId(String id);
}
