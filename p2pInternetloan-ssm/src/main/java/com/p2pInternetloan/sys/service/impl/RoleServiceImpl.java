package com.p2pInternetloan.sys.service.impl;

import com.p2pInternetloan.base.utils.Query;
import com.p2pInternetloan.sys.dao.RoleDao;
import com.p2pInternetloan.sys.entity.Role;
import com.p2pInternetloan.sys.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * (Role)表服务实现类
 *
 * @author cpc
 * @since 2019-10-20 08:15:40
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleDao roleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    @Override
    public Role queryById(Integer roleId) {
        return this.roleDao.queryById(roleId);
    }

    /**
     * 通过query对象查询
     *
     * @param  query 分页查询对象 
     * @return 对象列表
     */
    @Override
    public List<Role> queryPager(Query query) {
        return this.roleDao.queryPager(query);
    }

    /**
     * 新增数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(Role role) {
        return this.roleDao.insert(role);
    }

    /**
     * 修改数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    @Override
    public int update(Role role) {
        return this.roleDao.update(role);
    }

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 是否成功
     */
    @Override
    public int deleteById(Integer roleId) {
        return this.roleDao.deleteById(roleId);
    }

    @Override
    public Role queryByName(String roleName) {
        return this.roleDao.queryByName(roleName);
    }

    @Override
    public List<Map> getRoleSelect() {
        return this.roleDao.getRoleSelect();
    }


}