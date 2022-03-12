package com.jiangxia.eduservice.client;

import com.jiangxia.commonutils.ResultData;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author jiangxia
 * @date 2022年03月12日 15:31
 * desc:@component （把普通pojo实例化到spring容器中，相当于配置文件中的 <bean id="" class=""/>）
 * 泛指各种组件，就是说当我们的类不属于各种归类的时候（不属于@Controller、@Services等的时候），我们就可以使用@Component来标注这个类。
 */
@Component
public class VodFileDegradeFeignClient implements VodClient {
    //调用删除视频的服务出错之后执行该方法
    @Override
    public ResultData removeVideo(String videoId) {
        return ResultData.error().message("删除视频出错了");
    }

    //调用批量删除视频的服务出错之后执行的方法
    @Override
    public ResultData deleteBatch(List<String> videoIdList) {
        return ResultData.error().message("删除多个视频出错了");
    }
}
