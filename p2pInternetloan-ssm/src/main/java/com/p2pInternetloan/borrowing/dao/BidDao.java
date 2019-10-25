package com.p2pInternetloan.borrowing.dao;

import com.p2pInternetloan.borrowing.entity.Bid;
import org.apache.ibatis.annotations.Param;
import com.p2pInternetloan.base.utils.Query;
import java.util.List;

/**
 * (Bid)表数据库访问层
 *
 * @author cpc
 * @since 2019-10-23 15:03:53
 */
public interface BidDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Bid queryById(Long id);

    /**
     * 通过query对象查询
     *
     * @param  query 分页查询对象 
     * @return 对象列表
     */
    List<Bid> queryPager(Query query);

    /**
     * 新增数据
     *
     * @param bid 实例对象
     * @return 影响行数
     */
    int insert(Bid bid);

    /**
     * 修改数据
     *
     * @param bid 实例对象
     * @return 影响行数
     */
    int update(Bid bid);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}