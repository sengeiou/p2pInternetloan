package com.p2pInternetloan.members.service;

import com.p2pInternetloan.members.entity.Members;
import java.util.List;
import com.p2pInternetloan.base.utils.Query;

/**
 * (Members)表服务接口
 *
 * @author cpc
 * @since 2019-10-25 16:10:56
 */
public interface MembersService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Members queryById(Integer id);


    /**
     * 新增数据
     *
     * @param members 实例对象
     * @return 添加行数
     */
     int insert(Members members);

    /**
     * 通过query对象查询
     *
     * @param  query 分页查询对象 
     * @return 对象列表
     */
    List<Members> queryPager(Query query);


    /**
     * 修改数据
     *
     * @param members 实例对象
     * @return 修改行数
     */
     int update(Members members);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 删除行数
     */
    int deleteById(Integer id);

}