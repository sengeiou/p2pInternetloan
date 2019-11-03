package com.p2pInternetloan.borrowing.controller;

import com.p2pInternetloan.base.constant.BorrowingConstant;
import com.p2pInternetloan.base.utils.*;
import com.p2pInternetloan.borrowing.entity.BidRequest;
import com.p2pInternetloan.borrowing.entity.RefundDetail;
import com.p2pInternetloan.borrowing.service.BidRequestService;
import com.p2pInternetloan.borrowing.service.RefundDetailService;
import com.p2pInternetloan.members.entity.Members;
import com.p2pInternetloan.members.service.MembersService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 借款表 (BidRequest)表控制层
 *
 * @author cpc
 * @since 2019-10-31 14:37:28
 */
@RestController
@RequestMapping("/borrowing/bidRequest")
public class BidRequestController {

    @Resource
    private BidRequestService bidRequestService;
    @Resource
    private RefundDetailService refundDetailService;

    @Resource
    private MembersService membersService;

    /**
     * 这是根据id查询单个
     * @param id
     * @return
     */
    @GetMapping("/queryById")
    public BidRequest queryById(Integer id){
        return this.bidRequestService.queryById(id);
    }
    /**
     * 分页查询
     *
     * @param  params 请求参数集
     * @return 结果集封装对象 
     */
    @GetMapping("queryPager")
    public  PageUtils queryPager(@RequestParam Map<String, Object> params) {
         Query query = new Query(params);
         List<BidRequest> list = this.bidRequestService.queryPager(query);
         return new PageUtils(list, query.getTotal());
    }


    /**
     * 这是查询用户的所有借款
     *
     * @param  params 请求参数集
     * @return 结果集封装对象
     */
    @GetMapping("membersBidRequest")
    public  PageUtils membersBidRequest(@RequestParam Map<String, Object> params){
        Query query = new Query(params);
        //这是将用户id放入
        query.put("applierId", JwtSession.getCurrentMembersId());
        List<BidRequest> list = this.bidRequestService.queryPager(query);
        return new PageUtils(list, query.getTotal());
    }



    /**
     * 获取到当前用户借款项目
     * @return
     */
    @GetMapping("/getCurrentBidRequest")
    public PageUtils getCurrentBidRequest(){
        BidRequest currentBidRequest = this.bidRequestService.getCurrentBidRequest(JwtSession.getCurrentMembersId());
        List list = new ArrayList(1);
        list.add(currentBidRequest);
        //使用这种方式是应为字典翻译的问题
        return new PageUtils(list, 1);
    }

    /**
     * 这是申请信用贷调用的方法
     * @param bidRequest
     * @return
     */
    @PostMapping("applicationCreditLoan")
    public R applicationCreditLoan(BidRequest bidRequest){
        //加上当前用户
        bidRequest.setMembersId(JwtSession.getCurrentMembersId());
        bidRequest.setApplierId(JwtSession.getCurrentMembersId());
        bidRequest.setApplyTime(new Date());
        //设置借贷类型
        bidRequest.setBidRequestType(BorrowingConstant.CREDIT_LOAN);
        //向数据库中插入当前用户的借款项目
        this.bidRequestService.insert(bidRequest);
        //设置当前用户的借款项目
        Members members = new Members();
        members.setId(JwtSession.getCurrentMembersId());
        members.setBidRequestId(this.bidRequestService.getMaxId());
        this.membersService.update(members);
        return R.ok("申请成功");
    }
    
    
    /**
     * 修改数据
     * 说明：修改状态依赖这个东东
     * @param bidRequest 实例对象
     * @return R 
     */
    @PostMapping("update")
    public  R update(BidRequest bidRequest) {
        //这里判断状态(这是满标审核通过后的还款中)
        if(bidRequest.getBidRequestState() == 4){
            //1、生成用户还款明细表（好让用户还款）
            List<RefundDetail> refundDetails = this.refundDetailService.calculationRefundDetail(bidRequest);
            //判断还款类型是否为到本还本息
            if(bidRequest.getReturnType() == BorrowingConstant.MATURITY_PRINCIPAL){
                RefundDetail refundDetail = refundDetails.get(0);
                refundDetail.setBidRequestId(bidRequest.getId());
                //设置借款用户
                refundDetail.setMembersId(JwtSession.getCurrentMembersId());
                //计算时间
                refundDetail.setDeadLine(RepaymentDateUtil.getDate(bidRequest.getMonthesReturn()));
                //添加数据
                this.refundDetailService.insert(refundDetail);
            }else {
                for (int i = 0; i < refundDetails.size(); i++) {
                    RefundDetail refundDetail = refundDetails.get(i);
                    //设置当前的借款项目id
                    refundDetail.setBidRequestId(bidRequest.getId());
                    //设置借款用户
                    refundDetail.setMembersId(JwtSession.getCurrentMembersId());
                    //计算时间
                    refundDetail.setDeadLine(RepaymentDateUtil.getDate(i + 1));
                    this.refundDetailService.insert(refundDetail);
                }
            }
            //这是满标审核失败的清空
        }else if (bidRequest.getBidRequestState() == 8){
            //回款吧
            this.bidRequestService.failedPayment(bidRequest.getId());
        }
        return R.update(this.bidRequestService.update(bidRequest));
    }


    /**
     * 满标审核通过
     * @param bidRequest
     * @return
     */
    @PostMapping("fullMarkYes")
    public R fullMarkYes(BidRequest bidRequest){
        //生成客户的还款计划
        List<RefundDetail> refundDetails = this.refundDetailService.calculationRefundDetail(bidRequest);
        //插入到数据库中去啦
        for (RefundDetail refundDetail : refundDetails){
            this.refundDetailService.insert(refundDetail);
        }
        bidRequest.setBidRequestState(4);
        //修改标的状态
        return R.update(this.bidRequestService.update(bidRequest));
    }



    /**
     * 满标审核通失败
     * @param bidRequest
     * @return
     */
    @RequestMapping("fullMarkNo")
    public R fullMarkNo(BidRequest bidRequest){
        //将其他用户投资的钱还回去
        bidRequest.setBidRequestState(8);
        //回款
        this.bidRequestService.failedPayment(bidRequest.getMembersId());
        return R.update(this .bidRequestService.update(bidRequest));
    }


}