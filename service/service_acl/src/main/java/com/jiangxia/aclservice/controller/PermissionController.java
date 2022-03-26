package com.jiangxia.aclservice.controller;

import com.jiangxia.aclservice.entity.Permission;
import com.jiangxia.aclservice.service.PermissionService;
import com.jiangxia.commonutils.ResultData;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** 
 * @description: 权限 菜单管理
 * @param: null 
 * @return:  
 * @author:江夏
 * @date: 2022/3/26 20:44
 */ 
@RestController
@RequestMapping("/admin/acl/permission")
//@CrossOrigin
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    //获取全部菜单
    @ApiOperation(value = "查询所有菜单")
    @GetMapping
    public ResultData indexAllPermission() {
        List<Permission> list =  permissionService.queryAllMenu();
        return ResultData.ok().data("children",list);
    }

    @ApiOperation(value = "递归删除菜单")
    @DeleteMapping("remove/{id}")
    public ResultData remove(@PathVariable String id) {
        permissionService.removeChildById(id);
        return ResultData.ok();
    }

    @ApiOperation(value = "给角色分配权限")
    @PostMapping("/doAssign")
    public ResultData doAssign(String roleId,String[] permissionId) {
        permissionService.saveRolePermissionRealtionShipGuli(roleId,permissionId);
        return ResultData.ok();
    }

    @ApiOperation(value = "根据角色获取菜单")
    @GetMapping("toAssign/{roleId}")
    public ResultData toAssign(@PathVariable String roleId) {
        List<Permission> list = permissionService.selectAllMenu(roleId);
        return ResultData.ok().data("children", list);
    }



    @ApiOperation(value = "新增菜单")
    @PostMapping("save")
    public ResultData save(@RequestBody Permission permission) {
        permissionService.save(permission);
        return ResultData.ok();
    }

    @ApiOperation(value = "修改菜单")
    @PutMapping("update")
    public ResultData updateById(@RequestBody Permission permission) {
        permissionService.updateById(permission);
        return ResultData.ok();
    }

}

