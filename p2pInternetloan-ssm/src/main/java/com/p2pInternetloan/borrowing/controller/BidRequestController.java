package com.p2pInternetloan.borrowing.controller;

import com.p2pInternetloan.base.utils.R;
import com.p2pInternetloan.borrowing.entity.BidRequest;
import com.p2pInternetloan.borrowing.service.BidRequestService;
import org.springframework.web.bind.annotation.*;
import com.p2pInternetloan.base.utils.Query;
import com.p2pInternetloan.base.utils.PageUtils;
import java.util.Map;
import javax.annotation.Resource;
import java.util.List;

/**
 * (BidRequest)表控制层
 *
 * @author cpc
 * @since 2019-10-25 11:14:57
 */
@RestController
@RequestMapping("bidRequest")
public class BidRequestController {
    /**
     * 服务对象
     */
    @Resource
    private BidRequestService bidRequestService;



    /**
     * 分页查询
     *
     * @param  params 请求参数集
     * @return 结果集封装对象 
     */
    @GetMapping("queryPager")
    public  PageUtils queryPager(@RequestParam Map<String, Object> params) {
         Query query = new Query(params);
         List<BidRequest> list = bidRequestService.queryPager(query);
         return new PageUtils(list, query.getTotal());
    }

    

}