package com.jiangxia.eduservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.xml.transform.Result;

/**
 *
 * @author jiangxia
 * @date 2022/3/8 22:09
 * @param: No such property: code for class: Script1
 * @return No such property: code for class: Script1
 * @description:调用vod中的删除视频的方法
 */
@FeignClient("service-vod")
@Component
public interface VodClient {
    //定义调用的接口的路径，删除视频中的接口
    @DeleteMapping(value = "/eduvod/vod/video/{videoId}")
    public Result removeVideo(@PathVariable("videoId") String videoId);
}
