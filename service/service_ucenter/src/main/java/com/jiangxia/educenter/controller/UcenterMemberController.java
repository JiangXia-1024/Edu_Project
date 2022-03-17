package com.jiangxia.educenter.controller;

import com.jiangxia.commonutils.JwtUtils;
import com.jiangxia.commonutils.ResultData;
import com.jiangxia.educenter.entity.UcenterMember;
import com.jiangxia.educenter.entity.vo.RegisterVo;
import com.jiangxia.educenter.service.UcenterMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author jiangxia
 * @date 2022年03月15日 12:41
 * dfesc:登录注册controller
 */
@RestController
@RequestMapping("/educenter/member")
@CrossOrigin
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService memberService;

    //登录
    @PostMapping("login")//注意这里使用RequestBody 需要使用post提交 get提交会找不到RequestBody的参数
    public ResultData loginUser(@RequestBody UcenterMember member) {
        //member对象封装手机号和密码
        //调用service方法实现登录
        //返回token值，使用jwt生成
        String token = memberService.login(member);
        return ResultData.ok().data("token",token);
    }

    //注册
    @PostMapping("register")
    public ResultData registerUser(@RequestBody RegisterVo registerVo) {
        memberService.register(registerVo);
        return ResultData.ok();
    }

    //根据token获取用户信息
    @GetMapping("getMemberInfo")
    public ResultData getMemberInfo(HttpServletRequest request) {
        //调用jwt工具类的方法。根据request对象获取头信息，返回用户id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        //查询数据库根据用户id获取用户信息
        UcenterMember member = memberService.getById(memberId);
        return ResultData.ok().data("userInfo",member);
    }
}

