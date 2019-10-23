package com.p2pInternetloan.members.dao;

import com.p2pInternetloan.members.entity.MembersRealname;
import org.apache.ibatis.annotations.Param;
import com.p2pInternetloan.base.utils.Query;
import java.util.List;

/**
 * (MembersRealname)表数据库访问层
 *
 * @author cpc
 * @since 2019-10-23 14:21:23
 */
public interface MembersRealnameDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    MembersRealname queryById(Long id);

    /**
     * 通过query对象查询
     *
     * @param  query 分页查询对象 
     * @return 对象列表
     */
    List<MembersRealname> queryPager(Query query);

    /**
     * 新增数据
     *
     * @param membersRealname 实例对象
     * @return 影响行数
     */
    int insert(MembersRealname membersRealname);

    /**
     * 修改数据
     *
     * @param membersRealname 实例对象
     * @return 影响行数
     */
    int update(MembersRealname membersRealname);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}