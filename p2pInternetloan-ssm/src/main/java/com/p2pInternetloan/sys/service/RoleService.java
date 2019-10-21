package com.p2pInternetloan.sys.service;

import com.p2pInternetloan.base.utils.Query;
import com.p2pInternetloan.sys.entity.Role;

import java.util.List;
import java.util.Map;

/**
 * (Role)表服务接口
 *
 * @author cpc
 * @since 2019-10-20 08:15:40
 */
public interface RoleService {

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    Role queryById(Integer roleId);


    /**
     * 新增数据
     *
     * @param role 实例对象
     * @return 添加行数
     */
     int insert(Role role);

    /**
     * 通过query对象查询
     *
     * @param  query 分页查询对象 
     * @return 对象列表
     */
    List<Role> queryPager(Query query);


    /**
     * 修改数据
     *
     * @param role 实例对象
     * @return 修改行数
     */
     int update(Role role);

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 删除行数
     */
    int deleteById(Integer roleId);


    /**
     * 查询指定角色名的角色
     * @param roleName
     * @return
     */
    Role queryByName(String roleName);



    /**
     * 获取角色下拉
     * @return
     */
    List<Map> getRoleSelect();



}