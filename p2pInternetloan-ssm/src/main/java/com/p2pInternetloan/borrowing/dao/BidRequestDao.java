package com.p2pInternetloan.borrowing.dao;

import com.p2pInternetloan.base.utils.Query;
import com.p2pInternetloan.borrowing.entity.BidRequest;

import java.util.List;

/**
 * (BidRequest)表数据库访问层
 *
 * @author cpc
 * @since 2019-10-31 14:03:42
 */
public interface BidRequestDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    BidRequest queryById(Integer id);

    /**
     * 通过query对象查询
     *
     * @param  query 分页查询对象 
     * @return 对象列表
     */
    List<BidRequest> queryPager(Query query);

    /**
     * 新增数据
     *
     * @param bidRequest 实例对象
     * @return 影响行数
     */
    int insert(BidRequest bidRequest);

    /**
     * 修改数据
     *
     * @param bidRequest 实例对象
     * @return 影响行数
     */
    int update(BidRequest bidRequest);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    BidRequest getCurrentBidRequest(Integer membersId);

    Integer getMaxId();
}