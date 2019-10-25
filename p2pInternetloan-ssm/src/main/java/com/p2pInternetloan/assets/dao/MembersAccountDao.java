package com.p2pInternetloan.assets.dao;

import com.p2pInternetloan.assets.entity.MembersAccount;
import org.apache.ibatis.annotations.Param;
import com.p2pInternetloan.base.utils.Query;
import java.util.List;

/**
 * (MembersAccount)表数据库访问层
 *
 * @author cpc
 * @since 2019-10-24 19:33:38
 */
public interface MembersAccountDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    MembersAccount queryById(Integer id);

    /**
     * 通过query对象查询
     *
     * @param  query 分页查询对象 
     * @return 对象列表
     */
    List<MembersAccount> queryPager(Query query);

    /**
     * 新增数据
     *
     * @param membersAccount 实例对象
     * @return 影响行数
     */
    int insert(MembersAccount membersAccount);

    /**
     * 修改数据
     *
     * @param membersAccount 实例对象
     * @return 影响行数
     */
    int update(MembersAccount membersAccount);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);



}