package com.p2pInternetloan.borrowing.service;

import com.p2pInternetloan.borrowing.entity.BidRequest;
import java.util.List;
import com.p2pInternetloan.base.utils.Query;

/**
 * (BidRequest)表服务接口
 *
 * @author cpc
 * @since 2019-10-25 11:14:57
 */
public interface BidRequestService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    BidRequest queryById(Integer id);


    /**
     * 新增数据
     *
     * @param bidRequest 实例对象
     * @return 添加行数
     */
     int insert(BidRequest bidRequest);

    /**
     * 通过query对象查询
     *
     * @param  query 分页查询对象 
     * @return 对象列表
     */
    List<BidRequest> queryPager(Query query);


    /**
     * 修改数据
     *
     * @param bidRequest 实例对象
     * @return 修改行数
     */
     int update(BidRequest bidRequest);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 删除行数
     */
    int deleteById(Integer id);

}