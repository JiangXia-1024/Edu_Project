package com.jiangxia.educenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiangxia.educenter.entity.UcenterMember;
import com.jiangxia.educenter.entity.vo.RegisterVo;

/**
 * @author jiangxia
 * @date 2022年03月15日 13:10
 */
public interface UcenterMemberService extends IService<UcenterMember> {
    //登录的方法
    String login(UcenterMember member);

    //注册的方法
    void register(RegisterVo registerVo);

    //根据openid判断是否有该微信会员信息
    UcenterMember getOpenIdMember(String openid);
}
