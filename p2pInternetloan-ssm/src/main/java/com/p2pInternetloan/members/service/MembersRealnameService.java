package com.p2pInternetloan.members.service;

import com.p2pInternetloan.members.entity.MembersRealname;
import java.util.List;
import com.p2pInternetloan.base.utils.Query;

/**
 * (MembersRealname)表服务接口
 *
 * @author cpc
 * @since 2019-10-23 15:51:06
 */
public interface MembersRealnameService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    MembersRealname queryById(Integer id);


    /**
     * 新增数据
     *
     * @param membersRealname 实例对象
     * @return 添加行数
     */
     int insert(MembersRealname membersRealname);

    /**
     * 通过query对象查询
     *
     * @param  query 分页查询对象 
     * @return 对象列表
     */
    List<MembersRealname> queryPager(Query query);


    /**
     * 修改数据
     *
     * @param membersRealname 实例对象
     * @return 修改行数
     */
     int update(MembersRealname membersRealname);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 删除行数
     */
    int deleteById(Integer id);

    /**
     * 修改状态
     * @param state
     * @return
     */
    int Changestatus (Integer state);

}