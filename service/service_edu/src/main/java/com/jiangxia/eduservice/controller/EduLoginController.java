package com.jiangxia.eduservice.controller;

import com.jiangxia.commonutils.ResultData;
import org.springframework.web.bind.annotation.*;

/**
 * @author jiangxia
 * @date 2022年02月18日 23:30
 * desc:登录
 */
@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin //解决跨域问题使用该注解，主流解决跨越问题的方法
public class EduLoginController {

    //login
    @PostMapping("login")
    public ResultData login(){
        return ResultData.ok().data("token","admin");
    }
    //info
    @GetMapping("info")
    public ResultData info(){
        //返回角色、名称、头像
        return ResultData.ok().data("roles","[管理员]").data("name","admin").data("avatar","https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic.51yuansu.com%2Fpic2%2Fcover%2F00%2F39%2F19%2F5812d9084269e_610.jpg&refer=http%3A%2F%2Fpic.51yuansu.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1647791526&t=f81bb1da0a7c19dd96a904e334fbc745");
    }
}
