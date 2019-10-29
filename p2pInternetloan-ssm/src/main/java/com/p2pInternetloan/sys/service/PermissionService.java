package com.p2pInternetloan.sys.service;

import com.p2pInternetloan.base.utils.Query;
import com.p2pInternetloan.sys.entity.Menu;
import com.p2pInternetloan.sys.entity.Permission;

import java.util.List;

/**
 * (Permission)表服务接口
 *
 * @author cpc
 * @since 2019-10-19 21:18:15
 */
public interface PermissionService {

    /**
     * 通过ID查询单条数据
     *
     * @param perId 主键
     * @return 实例对象
     */
    Permission queryById(Integer perId);


    /**
     * 新增数据
     *
     * @param permission 实例对象
     * @return 添加行数
     */
    int insert(Permission permission);

    /**
     * 通过query对象查询
     *
     * @param  query 分页查询对象
     * @return 对象列表
     */
    List<Permission> queryPager(Query query);


    /**
     * 修改数据
     *
     * @param permission 实例对象
     * @return 修改行数
     */
    int update(Permission permission);

    /**
     * 通过主键删除数据
     *
     * @param perId 主键
     * @return 删除行数
     */
    int deleteById(Integer perId);




    /**
     * 获取用户权限菜单
     * @return
     */
    List<Menu> queryUserMenu(Integer userId);


    /**
     *获取菜单
     * @return
     */
    List<Menu> queryModules();


    /**
     * 这是搞到角色对应的权限ID集合
     * @param roleId
     * @return
     */
    List<Integer> queryIdByRole(Integer roleId);

}