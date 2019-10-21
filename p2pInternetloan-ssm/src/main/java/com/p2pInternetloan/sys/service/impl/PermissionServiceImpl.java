package com.p2pInternetloan.sys.service.impl;

import com.p2pInternetloan.base.utils.Query;
import com.p2pInternetloan.sys.dao.PermissionDao;
import com.p2pInternetloan.sys.entity.Menu;
import com.p2pInternetloan.sys.entity.Permission;
import com.p2pInternetloan.sys.service.PermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Permission)表服务实现类
 *
 * @author cpc
 * @since 2019-10-19 21:18:15
 */
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {
    @Resource
    private PermissionDao permissionDao;

    /**
     * 通过ID查询单条数据
     *
     * @param perId 主键
     * @return 实例对象
     */
    @Override
    public Permission queryById(Integer perId) {
        return this.permissionDao.queryById(perId);
    }

    /**
     * 通过query对象查询
     *
     * @param  query 分页查询对象 
     * @return 对象列表
     */
    @Override
    public List<Permission> queryPager(Query query) {
        return this.permissionDao.queryPager(query);
    }

    /**
     * 新增数据
     *
     * @param permission 实例对象
     * @return 实例对象
     */
    @Override
    public Permission insert(Permission permission) {
        this.permissionDao.insert(permission);
        return permission;
    }

    /**
     * 修改数据
     *
     * @param permission 实例对象
     * @return 实例对象
     */
    @Override
    public Permission update(Permission permission) {
        this.permissionDao.update(permission);
        return this.queryById(permission.getPerId());
    }

    /**
     * 通过主键删除数据
     *
     * @param perId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer perId) {
        return this.permissionDao.deleteById(perId) > 0;
    }

    @Override
    public List<Menu> queryUserMenu(Integer userId) {
        //获取顶级节点
        List<Menu> list = this.permissionDao.queryChildern(-1, userId);
        if(list.size() > 0){
            list.forEach(menu ->{
                getChildern(menu, userId);
            });
        }
        return list;
    }

    /**
     * 递归获取用户子菜单
     * @param menu
     */
    private void getChildern(Menu menu, Integer userId){
        List<Menu> list = this.permissionDao.queryChildern(menu.getPerId(), userId);
        menu.setChildern(list);
        //如果还有子菜单继续遍历
        if(list.size() > 0){
            list.forEach(m ->{
                getChildern(m, userId);
            });
        }
    }
}