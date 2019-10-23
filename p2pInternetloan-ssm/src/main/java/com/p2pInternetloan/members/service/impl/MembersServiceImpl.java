package com.p2pInternetloan.members.service.impl;

import com.p2pInternetloan.members.entity.Members;
import com.p2pInternetloan.members.dao.MembersDao;
import com.p2pInternetloan.members.service.MembersService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import com.p2pInternetloan.base.utils.Query;

/**
 * (Members)表服务实现类
 *
 * @author cpc
 * @since 2019-10-23 14:21:23
 */
@Service("membersService")
public class MembersServiceImpl implements MembersService {
    @Resource
    private MembersDao membersDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Members queryById(Long id) {
        return this.membersDao.queryById(id);
    }

    /**
     * 通过query对象查询
     *
     * @param  query 分页查询对象 
     * @return 对象列表
     */
    @Override
    public List<Members> queryPager(Query query) {
        return this.membersDao.queryPager(query);
    }

    /**
     * 新增数据
     *
     * @param members 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(Members members) {
        return this.membersDao.insert(members);
    }

    /**
     * 修改数据
     *
     * @param members 实例对象
     * @return 实例对象
     */
    @Override
    public int update(Members members) {
        return this.membersDao.update(members);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public int deleteById(Long id) {
        return this.membersDao.deleteById(id);
    }
}