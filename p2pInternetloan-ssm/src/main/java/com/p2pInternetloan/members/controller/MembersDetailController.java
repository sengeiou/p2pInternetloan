package com.p2pInternetloan.members.controller;

import com.p2pInternetloan.base.utils.PageUtils;
import com.p2pInternetloan.base.utils.Query;
import com.p2pInternetloan.base.utils.R;
import com.p2pInternetloan.members.entity.MembersDetail;
import com.p2pInternetloan.members.service.MembersDetailService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * (MembersDetail)表控制层
 *
 * @author cpc
 * @since 2019-10-26 16:09:20
 */
@RestController
@RequestMapping("membersDetail/")
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


    /**
     * 查单个
     * @param id
     * @return
     */
    @GetMapping("queryById")
    public MembersDetail queryById(Integer id){
        System.out.println(id);
        System.out.println("==================mei"+id);
        return this.membersDetailService.queryById(id);

    }

    /**
     * 修改字段项
     * @param
     * @return
     */
    @PostMapping("update")
    public R update(MembersDetail membersDetail){
        return R.update(this.membersDetailService.update(membersDetail));
    }

    /**
     * 修改字段项
     * @param
     * @return
     */
    @PostMapping("add")
    public R add(MembersDetail membersDetail){
        return R.update(this.membersDetailService.insert(membersDetail));
    }
}