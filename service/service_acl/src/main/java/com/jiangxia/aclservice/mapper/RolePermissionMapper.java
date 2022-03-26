package com.jiangxia.aclservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiangxia.aclservice.entity.Permission;
import com.jiangxia.aclservice.entity.RolePermission;

import java.util.List;

/**
 * @author:江夏
 * @date 2022/3/26 20:44
 * @version 1.0
 * @description: 角色权限 Mapper 接口
 */
public interface RolePermissionMapper extends BaseMapper<RolePermission> {
    List<String> selectPermissionValueByUserId(String id);

    List<String> selectAllPermissionValue();

    List<Permission> selectPermissionByUserId(String userId);
}
