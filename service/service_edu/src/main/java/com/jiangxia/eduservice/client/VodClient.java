package com.jiangxia.eduservice.client;

import com.jiangxia.commonutils.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.xml.transform.Result;
import java.util.List;

/**
 *
 * @author jiangxia
 * @date 2022/3/8 22:09
 * @param: No such property: code for class: Script1
 * @return No such property: code for class: Script1
 * @description:调用vod中的删除视频的方法
 * name :调用服务的名称，fallback：调用服务出错调用的类，该类是本接口的实现类
 */
@FeignClient(name="service-vod",fallback = VodFileDegradeFeignClient.class)
@Component
public interface VodClient {
    //定义调用的方法路径
    //根据视频id删除阿里云视频
    //@PathVariable注解一定要指定参数名称，否则会出错
    @DeleteMapping(value = "/eduvod/vod/video/{videoId}")
    public ResultData removeVideo(@PathVariable("videoId") String videoId);

    //定义调用删除多个视频的方法
    //删除多个阿里云视频的方法
    //参数多个视频id  List videoIdList
    @DeleteMapping("/eduvod/video/delete-batch")
    public ResultData deleteBatch(@RequestParam("videoIdList") List<String> videoIdList);
}
