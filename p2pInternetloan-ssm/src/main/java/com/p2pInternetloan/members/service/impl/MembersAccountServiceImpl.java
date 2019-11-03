package com.p2pInternetloan.members.service.impl;

import com.p2pInternetloan.base.utils.Query;
import com.p2pInternetloan.members.dao.MembersAccountDao;
import com.p2pInternetloan.members.entity.MembersAccount;
import com.p2pInternetloan.members.service.MembersAccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (MembersAccount)表服务实现类
 *
 * @author cpc
 * @since 2019-10-26 16:31:44
 */
@Service("membersAccountService")
public class MembersAccountServiceImpl implements MembersAccountService {
    @Resource
    private MembersAccountDao membersAccountDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public MembersAccount queryById(Integer id) {
        return this.membersAccountDao.queryById(id);
    }

    @Override
    public MembersAccount queryByMembersId(Integer MembersId) {
        return this.membersAccountDao.queryByMembersId(MembersId);
    }

    /**
     * 通过query对象查询
     *
     * @param  query 分页查询对象 
     * @return 对象列表
     */
    @Override
    public List<MembersAccount> queryPager(Query query) {
        return this.membersAccountDao.queryPager(query);
    }

    /**
     * 新增数据
     *
     * @param membersAccount 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(MembersAccount membersAccount) {
        return this.membersAccountDao.insert(membersAccount);
    }

    /**
     * 修改数据
     *
     * @param membersAccount 实例对象
     * @return 实例对象
     */
    @Override
    public int update(MembersAccount membersAccount) {
        return this.membersAccountDao.update(membersAccount);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public int deleteById(Integer id) {
        return this.membersAccountDao.deleteById(id);
    }
}