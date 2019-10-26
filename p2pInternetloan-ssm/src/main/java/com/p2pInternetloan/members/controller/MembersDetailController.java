package com.p2pInternetloan.members.controller;

import com.p2pInternetloan.members.entity.MembersDetail;
import com.p2pInternetloan.members.service.MembersDetailService;
import org.springframework.web.bind.annotation.*;
import com.p2pInternetloan.base.utils.Query;
import com.p2pInternetloan.base.utils.PageUtils;
import java.util.Map;
import javax.annotation.Resource;
import java.util.List;

/**
 * (MembersDetail)表控制层
 *
 * @author cpc
 * @since 2019-10-26 16:09:20
 */
@RestController
@RequestMapping("membersDetail")
public class MembersDetailController {
    /**
     * 服务对象
     */
    @Resource
    private MembersDetailService membersDetailService;

    /**
     * 分页查询
     *
     * @param  params 请求参数集
     * @return 结果集封装对象 
     */
    @GetMapping("queryPager")
    public  PageUtils queryPager(@RequestParam Map<String, Object> params) {
         Query query = new Query(params);
         List<MembersDetail> list = membersDetailService.queryPager(query);
         return new PageUtils(list, query.getTotal());
    }
}