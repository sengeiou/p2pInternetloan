package com.p2pInternetloan.sys.service.impl;

import com.p2pInternetloan.base.utils.Query;
import com.p2pInternetloan.sys.dao.UserDao;
import com.p2pInternetloan.sys.entity.User;
import com.p2pInternetloan.sys.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * (User)表服务实现类
 *
 * @author cpc
 * @since 2019-10-19 17:34:51
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    /**
     * 通过ID查询单条数据
     *
     * @param userid 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Integer userid) {
        return this.userDao.queryById(userid);
    }

    /**
     * 通过query对象查询
     *
     * @param  query 分页查询对象 
     * @return 对象列表
     */
    @Override
    public List<User> queryPager(Query query) {
        return this.userDao.queryPager(query);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(User user) {
        return this.userDao.insert(user);
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public int update(User user) {
        return this.userDao.update(user);
    }


    /**
     * 通过主键删除数据
     *
     * @param userid 主键
     * @return 是否成功
     */
    @Override
    public int deleteById(Integer userid) {
        return this.userDao.deleteById(userid);
    }

    @Override
    public Set<String> getPersByUserId(Integer userId) {
        return this.userDao.getRolesByUserId(userId);
    }

    @Override
    public User queryByName(String userName) {
        return this.userDao.queryByName(userName);
    }

    @Override
    public Set<String> getRolesByUserId(Integer userId) {
        return this.userDao.getRolesByUserId(userId);
    }
}