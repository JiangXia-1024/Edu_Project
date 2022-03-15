package com.jiangxia.msmservice.controller;

import com.jiangxia.commonutils.ResultData;
import com.jiangxia.msmservice.service.MsmService;
import com.jiangxia.msmservice.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author jiangxia
 * @date 2022年03月14日 12:46
 * desc：发送短信接口
 */
@RestController
@RequestMapping("/edumsm/msm")
@CrossOrigin //跨域
public class MsmController {
    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    //发送短信获取验证码
    @GetMapping("send/{phone}")
    public ResultData sendMsm(@PathVariable String phone){
//      1、从redis获取验证码，如果获取到则直接返回，使用redis设置过期时间
        String code = redisTemplate.opsForValue().get(phone);
        if(!StringUtils.isEmpty(code)){
            return ResultData.ok();
        }
//      2、如果redis获取不到验证码
        code = RandomUtil.getFourBitRandom();//获取四位随机数验证码
        Map<String,Object> param = new HashMap<>();
        param.put("code",code);
//      3、发送短信
        boolean isSendSuccess = msmService.send(param,phone);
        if(isSendSuccess){
            //发送成功，就把验证码存到redis中，并且设置有效时间5分钟有效
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            return ResultData.ok();
        }else{
            return ResultData.error().message("获取验证码失败");
        }
    }
}
