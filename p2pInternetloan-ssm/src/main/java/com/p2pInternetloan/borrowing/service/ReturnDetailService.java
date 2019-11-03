package com.p2pInternetloan.borrowing.service;

import com.p2pInternetloan.borrowing.entity.ReturnDetail;
import java.util.List;
import com.p2pInternetloan.base.utils.Query;

/**
 * (ReturnDetail)表服务接口
 *
 * @author cpc
 * @since 2019-10-31 14:03:43
 */
public interface ReturnDetailService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ReturnDetail queryById(Integer id);


    /**
     * 新增数据
     *
     * @param returnDetail 实例对象
     * @return 添加行数
     */
     int insert(ReturnDetail returnDetail);

    /**
     * 通过query对象查询
     *
     * @param  query 分页查询对象 
     * @return 对象列表
     */
    List<ReturnDetail> queryPager(Query query);


    /**
     * 修改数据
     *
     * @param returnDetail 实例对象
     * @return 修改行数
     */
     int update(ReturnDetail returnDetail);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 删除行数
     */
    int deleteById(Integer id);

}