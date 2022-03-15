package com.jiangxia.educenter.service;

import com.jiangxia.educenter.entity.UcenterMember;
import com.jiangxia.educenter.entity.vo.RegisterVo;

/**
 * @author jiangxia
 * @date 2022年03月15日 13:10
 */
public interface UcenterMemberService {
    //登录的方法
    String login(UcenterMember member);

    //注册的方法
    void register(RegisterVo registerVo);
}
