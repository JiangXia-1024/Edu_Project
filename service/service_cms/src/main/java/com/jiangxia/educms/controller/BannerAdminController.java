package com.jiangxia.educms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiangxia.commonutils.ResultData;
import com.jiangxia.educms.entity.CrmBanner;
import com.jiangxia.educms.service.CrmBannerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author jiangxia
 * @date 2022年03月13日 22:55
 * desc:后台banner管理接口
 */
@RestController
@RequestMapping("/educms/banneradmin")
@CrossOrigin
public class BannerAdminController {
    @Autowired
    private CrmBannerService bannerService;

    //1 分页查询banner
    @GetMapping("pageBanner/{page}/{limit}")
    public ResultData pageBanner(@PathVariable long page, @PathVariable long limit) {
        Page<CrmBanner> pageBanner = new Page<>(page,limit);
        bannerService.page(pageBanner,null);
        return ResultData.ok().data("items",pageBanner.getRecords()).data("total",pageBanner.getTotal());
    }

    //2 添加banner
    @PostMapping("addBanner")
    public ResultData addBanner(@RequestBody CrmBanner crmBanner) {
        bannerService.save(crmBanner);
        return ResultData.ok();
    }

    @ApiOperation(value = "获取Banner")
    @GetMapping("get/{id}")
    public ResultData get(@PathVariable String id) {
        CrmBanner banner = bannerService.getById(id);
        return ResultData.ok().data("item", banner);
    }

    @ApiOperation(value = "修改Banner")
    @PutMapping("update")
    public ResultData updateById(@RequestBody CrmBanner banner) {
        bannerService.updateById(banner);
        return ResultData.ok();
    }

    @ApiOperation(value = "删除Banner")
    @DeleteMapping("remove/{id}")
    public ResultData remove(@PathVariable String id) {
        bannerService.removeById(id);
        return ResultData.ok();
    }
}
