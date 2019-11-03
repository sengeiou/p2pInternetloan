package com.p2pInternetloan.borrowing.controller;

import com.p2pInternetloan.base.utils.PageUtils;
import com.p2pInternetloan.base.utils.Query;
import com.p2pInternetloan.base.utils.R;
import com.p2pInternetloan.borrowing.entity.ReturnDetail;
import com.p2pInternetloan.borrowing.service.ReturnDetailService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 借口明细表 (ReturnDetail)表控制层
 *
 * @author cpc
 * @since 2019-10-31 14:37:28
 */
@RestController
@RequestMapping("/borrowing/returnDetail")
public class ReturnDetailController {

    @Resource
    private ReturnDetailService returnDetailService;


    /**
     * 分页查询
     *
     * @param  params 请求参数集
     * @return 结果集封装对象 
     */
    @GetMapping("queryPager")
    public  PageUtils queryPager(@RequestParam Map<String, Object> params) {
         Query query = new Query(params);
         List<ReturnDetail> list = returnDetailService.queryPager(query);
         return new PageUtils(list, query.getTotal());
    }
    /**
     * 新增数据
     *
     * @param returnDetail 实例对象
     * @return R 
     */
    @PostMapping("add")
    public  R add(ReturnDetail returnDetail) {
        return R.update(returnDetailService.insert(returnDetail));
    }
    
    
    
    /**
     * 修改数据
     *
     * @param returnDetail 实例对象
     * @return R 
     */
    @PostMapping("update")
    public  R update(ReturnDetail returnDetail) {
        return R.update(returnDetailService.update(returnDetail));
    }
    
    /**
     * 删除数据
     *
     * @param id 主键
     * @return R 
     */
    @PostMapping("del")
    public  R del(Integer id) {
        return R.update(returnDetailService.deleteById(id));
    }
}