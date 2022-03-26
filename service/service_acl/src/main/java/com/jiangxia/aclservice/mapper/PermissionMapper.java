package com.jiangxia.aclservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiangxia.aclservice.entity.Permission;

import java.util.List;

/** 
 * @description: 权限 Mapper 接口
 * @param: null 
 * @return:  
 * @author:江夏
 * @date: 2022/3/26 20:45
 */ 
public interface PermissionMapper extends BaseMapper<Permission> {


    List<String> selectPermissionValueByUserId(String id);

    List<String> selectAllPermissionValue();

    List<Permission> selectPermissionByUserId(String userId);
}
