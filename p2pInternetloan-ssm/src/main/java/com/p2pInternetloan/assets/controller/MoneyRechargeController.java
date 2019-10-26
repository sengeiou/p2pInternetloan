package com.p2pInternetloan.assets.controller;

import com.p2pInternetloan.assets.entity.MembersAccount;
import com.p2pInternetloan.assets.entity.MoneyRecharge;
import com.p2pInternetloan.assets.service.MembersAccountService;
import com.p2pInternetloan.assets.service.MoneyRechargeService;
import com.p2pInternetloan.base.utils.R;
import org.springframework.web.bind.annotation.*;
import com.p2pInternetloan.base.utils.Query;
import com.p2pInternetloan.base.utils.PageUtils;
import java.util.Map;
import javax.annotation.Resource;
import java.util.List;

/**
 * (MoneyRecharge)表控制层   充值记录
 *
 * @author cpc
 * @since 2019-10-24 19:12:17
 */
@RestController
@RequestMapping("assets/moneyRecharge")
public class MoneyRechargeController {
    /**
     * 服务对象
     */
    @Resource
    private MoneyRechargeService moneyRechargeService;

    @Resource
    private MembersAccountService membersAccountService;

    /**
     * 分页查询
     *
     * @param  params 请求参数集
     * @return 结果集封装对象
     */
    @GetMapping("queryPager")
    public  PageUtils queryPager(@RequestParam Map<String, Object> params) {
         Query query = new Query(params);
         List<MoneyRecharge> list = moneyRechargeService.queryPager(query);
         return new PageUtils(list, query.getTotal());
    }


    /**
     * (充值)向会员账户余额表和充值记录表插入数据
     * @param membersAccount
     * @param moneyRecharge
     * @return
     */
    @PostMapping("setAmount")
    public R setAmount(MembersAccount membersAccount, MoneyRecharge moneyRecharge){
        this.membersAccountService.update(membersAccount);
        this.moneyRechargeService.update(moneyRecharge);
        return R.update(this.moneyRechargeService.updateAmount(membersAccount,moneyRecharge));
    }




}