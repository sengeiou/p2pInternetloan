package com.p2pInternetloan.borrowing.controller;

import com.p2pInternetloan.base.utils.PageUtils;
import com.p2pInternetloan.base.utils.Query;
import com.p2pInternetloan.base.utils.R;
import com.p2pInternetloan.borrowing.entity.BidRequest;
import com.p2pInternetloan.borrowing.entity.RefundDetail;
import com.p2pInternetloan.borrowing.service.RefundDetailService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 还款明细表  (RefundDetail)表控制层
 *
 * @author cpc
 * @since 2019-10-31 14:37:28
 */
@RestController
@RequestMapping("/borrowing/refundDetail")
public class RefundDetailController {

    @Resource
    private RefundDetailService refundDetailService;



    /**
     * 分页查询
     *
     * @param  params 请求参数集
     * @return 结果集封装对象 
     */
    @GetMapping("queryPager")
    public  PageUtils queryPager(@RequestParam Map<String, Object> params) {
         Query query = new Query(params);
         List<RefundDetail> list = refundDetailService.queryPager(query);
         return new PageUtils(list, query.getTotal());
    }


    /**
     * 新增数据
     *
     * @param refundDetail 实例对象
     * @return R 
     */
    @PostMapping("add")
    public  R add(RefundDetail refundDetail) {
        return R.update(refundDetailService.insert(refundDetail));
    }
    
    
    
    /**
     * 修改数据
     *
     * @param refundDetail 实例对象
     * @return R 
     */
    @PostMapping("update")
    public  R update(RefundDetail refundDetail) {
        return R.update(refundDetailService.update(refundDetail));
    }
    
    /**
     * 删除数据
     *
     * @param id 主键
     * @return R 
     */
    @PostMapping("del")
    public  R del(Integer id) {
        return R.update(refundDetailService.deleteById(id));
    }

    /**
     * 这是计算还款计划
     * @param bidRequest 这是计算对象
     * @return
     */
    @RequestMapping("calculationRefundDetail")
    public R calculationRefundDetail(BidRequest bidRequest){
        R ok = R.ok();
        ok.put("refundDetails", this.refundDetailService.calculationRefundDetail(bidRequest));
        return ok;
    }

    @PostMapping("/repayment")
    public R repayment(RefundDetail refundDetail){
        //还款
        return R.update(this.refundDetailService.update(refundDetail));
    }

}