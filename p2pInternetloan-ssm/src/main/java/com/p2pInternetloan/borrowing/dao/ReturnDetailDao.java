package com.p2pInternetloan.borrowing.dao;

import com.p2pInternetloan.borrowing.entity.ReturnDetail;
import org.apache.ibatis.annotations.Param;
import com.p2pInternetloan.base.utils.Query;
import java.util.List;

/**
 * (ReturnDetail)表数据库访问层
 *
 * @author cpc
 * @since 2019-10-31 14:03:43
 */
public interface ReturnDetailDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ReturnDetail queryById(Integer id);

    /**
     * 通过query对象查询
     *
     * @param  query 分页查询对象 
     * @return 对象列表
     */
    List<ReturnDetail> queryPager(Query query);

    /**
     * 新增数据
     *
     * @param returnDetail 实例对象
     * @return 影响行数
     */
    int insert(ReturnDetail returnDetail);

    /**
     * 修改数据
     *
     * @param returnDetail 实例对象
     * @return 影响行数
     */
    int update(ReturnDetail returnDetail);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}