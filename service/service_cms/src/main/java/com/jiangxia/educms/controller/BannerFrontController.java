package com.jiangxia.educms.controller;

import com.jiangxia.commonutils.ResultData;
import com.jiangxia.educms.entity.CrmBanner;
import com.jiangxia.educms.service.CrmBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author jiangxia
 * @date 2022年03月13日 22:55
 * desc:前台bannber显示
 */
@RestController
@RequestMapping("/educms/bannerfront")
@CrossOrigin
public class BannerFrontController {

    @Autowired
    private CrmBannerService bannerService;

    //查询所有banner
    @GetMapping("getAllBanner")
    public ResultData getAllBanner() {
        List<CrmBanner> list = bannerService.selectAllBanner();
        return ResultData.ok().data("list",list);
    }
}
