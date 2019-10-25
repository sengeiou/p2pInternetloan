package com.p2pInternetloan.assets.controller;

import com.p2pInternetloan.assets.entity.MembersAccount;
import com.p2pInternetloan.assets.entity.MoneyWithdraw;
import com.p2pInternetloan.assets.service.MembersAccountService;
import org.springframework.web.bind.annotation.*;
import com.p2pInternetloan.base.utils.Query;
import com.p2pInternetloan.base.utils.PageUtils;
import java.util.Map;
import javax.annotation.Resource;
import java.util.List;

/**
 * (MembersAccount)表控制层  会员账户信息表
 *
 * @author cpc
 * @since 2019-10-24 19:33:38
 */
@RestController
@RequestMapping("membersAccount")
public class MembersAccountController {
    /**
     * 服务对象
     */
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
         List<MembersAccount> list = membersAccountService.queryPager(query);
         return new PageUtils(list, query.getTotal());
    }



}