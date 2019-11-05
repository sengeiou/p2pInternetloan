package com.p2pInternetloan.borrowing.controller;
import com.p2pInternetloan.base.utils.JwtSession;
import com.p2pInternetloan.base.utils.PageUtils;
import com.p2pInternetloan.base.utils.Query;
import com.p2pInternetloan.base.utils.R;
import com.p2pInternetloan.borrowing.entity.Bid;
import com.p2pInternetloan.borrowing.entity.BidRequest;
import com.p2pInternetloan.borrowing.service.BidRequestService;
import com.p2pInternetloan.borrowing.service.BidService;
import com.p2pInternetloan.members.entity.Members;
import com.p2pInternetloan.members.entity.MembersAccount;
import com.p2pInternetloan.members.service.MembersAccountService;
import com.p2pInternetloan.members.service.MembersService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 投标表 (Bid)表控制层
 *
 * @author cpc
 * @since 2019-10-31 14:37:28
 */
@RestController
@RequestMapping("/borrowing/bid")
public class BidController {
    @Resource
    private BidService bidService;
    @Resource
    private MembersAccountService membersAccountService;
    @Resource
    private BidRequestService requestService;
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
         List<Bid> list = bidService.queryPager(query);
         return new PageUtils(list, query.getTotal());
    }

    /**
     * 分页查询 查询当前用户投标
     *
     * @param  params 请求参数集
     * @return 结果集封装对象
     */
    @GetMapping("membersBidQueryPager")
    public  PageUtils membersBidQueryPager(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        //放入会员id
        query.put("membersId", JwtSession.getCurrentMembersId());
        List list = bidService.membersBidQueryPager(query);
        return new PageUtils(list, query.getTotal());
    }






    /**
     * 新增数据 也就是投标
     *
     * @param bid 实例对象
     * @return R 
     */
    @PostMapping("tender")
    public  R tender(Bid bid, String pwd) {
        //这是查找出来当前用户的借贷对象
        MembersAccount membersAccount = this.membersAccountService.queryByMembersId(JwtSession.getCurrentMembersId());
        Members members = this.membersService.queryById(JwtSession.getCurrentMembersId());
        //这是借贷额度的比较
        int flag = membersAccount.getUsableAmount().compareTo(bid.getAvailableAmount());
        //账号是否有那么多的钱 如果没有就不要来借贷了 早点滚蛋 穷鬼
        if(flag == 1|| flag == 0){
            //记录当前时间
            bid.setBidTime(new Date());
            //设置投标人
            bid.setMembersId(JwtSession.getCurrentMembersId());
            //查找出客户要投资的借款对象
            BidRequest bidRequest = this.requestService.queryById(bid.getBidRequestId());
            //这是当投标后，该表会有多是钱（得判断一下呀）
            BigDecimal sum = bidRequest.getCurrentSum().add(bid.getAvailableAmount());
            //这是借贷表借贷的额度（也就是老子要借多是钱）
            BigDecimal bidRequestAmount = bidRequest.getBidRequestAmount();
            //判断一下
            flag = bidRequestAmount.compareTo(sum);
            if(flag == -1){
                return R.error("你的投标额度超了借款额度");
            }
            //这是密码校验
            if(!members.getPassword().equals(pwd)){
                return R.error("密码输入错误，请重新输入");
            }
            //修改借贷的当前收到投标的额度
            bidRequest.setCurrentSum(sum);
            //当前投资次数 ++
            bidRequest.setBidCount(bidRequest.getBidCount() +1);
            if(flag == 0){
                //这是当钱够了后将标的状态修改车 满标
                bidRequest.setBidRequestState(3);
            }
            //这是修改借口表中的收到的投标额度
            this.requestService.update(bidRequest);
            //添加
            return R.update(this.bidService.insert(bid));
        }
        return R.error("您当前的余额已不足,请充值后在来玩");
    }
    
    
    
    /**
     * 修改数据
     *
     * @param bid 实例对象
     * @return R 
     */
    @PostMapping("update")
    public  R update(Bid bid) {
        //记录当前登录会员的id
        bid.setMembersId(JwtSession.getCurrentMembersId());
        //记录当前投标时间
        bid.setBidTime(new Date());
        return R.update(bidService.update(bid));
    }
    
    /**
     * 删除数据
     *
     * @param id 主键
     * @return R 
     */
    @PostMapping("del")
    public  R del(Integer id) {
        return R.update(bidService.deleteById(id));
    }


    /**
     * 前台个人中心页投资计算
     * @return
     */
    @GetMapping("investmentCalculation")
    public R investmentCalculation(){
        R r = R.ok();
        r.put("data", this.bidService.investmentCalculation(JwtSession.getCurrentMembersId()));
        return r;
    }

}