package com.jiangxia.educms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiangxia.educms.entity.CrmBanner;

import java.util.List;

/**
 *
 * @author jiangxia
 * @date 2022/3/13 23:01
 * @param: No such property: code for class: Script1
 * @return No such property: code for class: Script1
 * @description:首页banner表 服务类
 */
public interface CrmBannerService extends IService<CrmBanner> {

    //查询所有banner
    List<CrmBanner> selectAllBanner();
}
