package com.jiangxia.staservice.client;

import com.jiangxia.commonutils.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author jiangxia
 * @date 2022年03月26日 23:16
 */
@Component
@FeignClient("service-ucenter")
public interface UcenterClient {
    //查询某一天注册人数
    @GetMapping("/educenter/member/countRegister/{day}")
    public ResultData countRegister(@PathVariable("day") String day);
}
