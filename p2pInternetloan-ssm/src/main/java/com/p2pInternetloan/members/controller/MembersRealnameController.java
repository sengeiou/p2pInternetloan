package com.p2pInternetloan.members.controller;

import com.p2pInternetloan.base.utils.PageUtils;
import com.p2pInternetloan.base.utils.Query;
import com.p2pInternetloan.base.utils.R;
import com.p2pInternetloan.members.entity.MembersRealname;
import com.p2pInternetloan.members.service.MembersRealnameService;
import org.springframework.web.bind.annotation.*;
import sun.plugin2.message.SetAppletSizeMessage;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * (MembersRealname)表控制层
 *
 * @author cpc
 * @since 2019-10-23 15:51:06
 */

@RestController
@RequestMapping("members/mem")
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
        System.out.println(list);
         return new PageUtils(list, query.getTotal());
    }

    /**
     * 添加字段项
     * @param membersrealname
     * @return
     */
//    @PostMapping("add")
    @RequestMapping("add")
    public R add(MembersRealname membersrealname){
        System.out.println(membersrealname);
        membersrealname.setApplyTime(new Date());
        return R.update(this.membersRealnameService.insert(membersrealname));
    }

    /**
     * 修改字段项
     * @param
     * @return
     */
    @PostMapping("update")
    public R update(MembersRealname membersRealname){
        return R.update(this.membersRealnameService.update(membersRealname));
    }


//    /**
//     * 删除字典项
//     * @param id
//     * @return
//     */
//    @PostMapping("del/{id}")
//    @RequiresPermissions(value = {"member:user:view"})
//    public R del(@PathVariable("id") Integer id){
//        return R.update(this.membersRealnameService.deleteById(id));
//    }









}