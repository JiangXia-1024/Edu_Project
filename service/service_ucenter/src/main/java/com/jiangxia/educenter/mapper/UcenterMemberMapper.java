package com.jiangxia.educenter.mapper;

import com.jiangxia.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/** 
 * @description: 会员表 Mapper 接口
 * @param: null 
 * @return:  
 * @author:江夏
 * @date: 2022/3/26 23:16
 */ 
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {
    //查询某一天注册人数
    Integer countRegisterDay(String day);
}
