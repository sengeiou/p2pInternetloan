package com.p2pInternetloan.members.controller;

import com.p2pInternetloan.members.entity.MembersRealname;
import com.p2pInternetloan.members.service.MembersRealnameService;
import org.springframework.web.bind.annotation.*;
import com.p2pInternetloan.base.utils.Query;
import com.p2pInternetloan.base.utils.PageUtils;
import java.util.Map;
import javax.annotation.Resource;
import java.util.List;

/**
 * (MembersRealname)表控制层
 *
 * @author cpc
 * @since 2019-10-23 14:21:24
 */
@RestController
@RequestMapping("membersRealname")
public class MembersRealnameController {
    /**
     * 服务对象
     */
    @Resource
    private MembersRealnameService membersRealnameService;

    /**
     * 分页查询
     *
     * @param  params 请求参数集
     * @return 结果集封装对象 
     */
    @GetMapping("queryPager")
    public  PageUtils queryPager(@RequestParam Map<String, Object> params) {
         Query query = new Query(params);
         List<MembersRealname> list = membersRealnameService.queryPager(query);
         return new PageUtils(list, query.getTotal());
    }
}