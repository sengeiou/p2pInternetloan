package com.p2pInternetloan.assets.controller;

import com.p2pInternetloan.assets.entity.Members;
import com.p2pInternetloan.assets.service.MembersService;
import org.springframework.web.bind.annotation.*;
import com.p2pInternetloan.base.utils.Query;
import com.p2pInternetloan.base.utils.PageUtils;
import java.util.Map;
import javax.annotation.Resource;
import java.util.List;

/**
 * (Members)表控制层
 *
 * @author cpc
 * @since 2019-10-24 22:01:26
 */
@RestController
@RequestMapping("members")
public class MembersController {
    /**
     * 服务对象
     */
    @Resource
    private MembersService membersService;

    /**
     * 分页查询
     *
     * @param  params 请求参数集
     * @return 结果集封装对象 
     */
    @GetMapping("queryPager")
    public  PageUtils queryPager(@RequestParam Map<String, Object> params) {
         Query query = new Query(params);
         List<Members> list = membersService.queryPager(query);
         return new PageUtils(list, query.getTotal());
    }
}