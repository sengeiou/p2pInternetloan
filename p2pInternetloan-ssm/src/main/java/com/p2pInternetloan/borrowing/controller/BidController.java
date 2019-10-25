package com.p2pInternetloan.borrowing.controller;

import com.p2pInternetloan.borrowing.entity.Bid;
import com.p2pInternetloan.borrowing.service.BidService;
import org.springframework.web.bind.annotation.*;
import com.p2pInternetloan.base.utils.Query;
import com.p2pInternetloan.base.utils.PageUtils;
import java.util.Map;
import javax.annotation.Resource;
import java.util.List;

/**
 * (Bid)表控制层
 *
 * @author cpc
 * @since 2019-10-23 15:03:53
 */
@RestController
@RequestMapping("bid")
public class BidController {
    /**
     * 服务对象
     */
    @Resource
    private BidService bidService;

    /**
     * 分页查询
     *
     * @param  params 请求参数集
     * @return 结果集封装对象 
     */
    @GetMapping("queryPager")
    public  PageUtils queryPager(@RequestParam Map<String, Object> params) {
         Query query = new Query(params);

         List<Bid> list = bidService.queryPager(query);
         return new PageUtils(list, query.getTotal());
    }
}