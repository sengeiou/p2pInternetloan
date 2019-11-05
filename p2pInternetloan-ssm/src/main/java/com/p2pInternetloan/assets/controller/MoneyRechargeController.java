package com.p2pInternetloan.assets.controller;

import com.p2pInternetloan.assets.service.MoneyRechargeService;
import com.p2pInternetloan.base.utils.JwtSession;
import com.p2pInternetloan.base.utils.PageUtils;
import com.p2pInternetloan.base.utils.Query;
import com.p2pInternetloan.base.utils.R;
import com.p2pInternetloan.members.entity.MembersAccount;
import com.p2pInternetloan.members.service.MembersAccountService;
import org.springframework.web.bind.annotation.*;
import com.p2pInternetloan.assets.entity.MoneyRecharge;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
     * 查单个
     * @param moneyrechargeid
     * @return
     */
    @GetMapping("queryByid")
    public MoneyRecharge queryById(Integer moneyrechargeid){

        return this.moneyRechargeService.queryById(moneyrechargeid);
    }


    /**
     * 这是充值的方法
     * @param moneyRecharge
     * @return
     */
    @PostMapping("recharge")
    public R recharge(MoneyRecharge moneyRecharge){
        MembersAccount membersAccount = membersAccountService.queryByMembersId(JwtSession.getCurrentMembersId());
        membersAccount.setUsableAmount(membersAccount.getUsableAmount().add(moneyRecharge.getAmount()));
        membersAccountService.update(membersAccount);
        //记录当前时间
        moneyRecharge.setTradeTime(new Date());
        //记录会员id
        moneyRecharge.setMembersId(JwtSession.getCurrentMembersId());
        //天津充值记录
        return R.update(this.moneyRechargeService.insert(moneyRecharge));
    }
}



