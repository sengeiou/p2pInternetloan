package com.p2pInternetloan.setting.controller;

import com.p2pInternetloan.base.utils.PageUtils;
import com.p2pInternetloan.base.utils.Query;
import com.p2pInternetloan.setting.entity.Setting;
import com.p2pInternetloan.setting.service.SettingService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * (Setting)表控制层
 *
 * @author cpc
 * @since 2019-10-22 21:16:54
 */
@RestController
@RequestMapping("setting")
public class SettingController {
    /**
     * 服务对象
     */
    @Resource
    private SettingService settingService;

    /**
     * 分页查询
     *
     * @param  params 请求参数集
     * @return 结果集封装对象 
     */
    @GetMapping("queryPager")
    public  PageUtils queryPager(@RequestParam Map<String, Object> params) {
         Query query = new Query(params);
         List<Setting> list = settingService.queryPager(query);
         return new PageUtils(list, query.getTotal());
    }

    /**
     * 设置用户初始额度
     * @param useableminlimit
     * @return
     */
    @PostMapping("setMinLimit")
    public int setMinLimit(Setting useableminlimit){
        return this.settingService.update(useableminlimit);
    }


    /***
     * 设置会员还款费率
     * @param reimbursementrates
     * @return
     */
    @PostMapping("setReimbursementRates")
    public int setReimbursementRates(Setting reimbursementrates){
        return this.settingService.update(reimbursementrates);
    }

    /**
     * 设置借款手续费率
     * @param requestPrecedurelimit
     * @return
     */
    @PostMapping("setRequestPrecedureLimit")
    public int setRequestPrecedureLimit(Setting requestPrecedurelimit){
        return this.settingService.update(requestPrecedurelimit);
    }


    /**
     * 设置逾期费率
     * @param overdue
     * @return
     */
    @PostMapping("setOverdue")
    public int setOverdue(Setting overdue){
        return this.settingService.update(overdue);
    }


    

}