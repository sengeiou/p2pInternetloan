package com.p2pInternetloan.borrowing.service.impl;

import com.p2pInternetloan.borrowing.entity.MembersRealname;
import com.p2pInternetloan.borrowing.dao.MembersRealnameDao;
import com.p2pInternetloan.borrowing.service.MembersRealnameService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import com.p2pInternetloan.base.utils.Query;

/**
 * (MembersRealname)表服务实现类
 *
 * @author cpc
 * @since 2019-10-23 15:02:10
 */
@Service("membersRealnameService")
public class MembersRealnameServiceImpl implements MembersRealnameService {
    @Resource
    private MembersRealnameDao membersRealnameDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public MembersRealname queryById(Long id) {
        return this.membersRealnameDao.queryById(id);
    }

    /**
     * 通过query对象查询
     *
     * @param  query 分页查询对象 
     * @return 对象列表
     */
    @Override
    public List<MembersRealname> queryPager(Query query) {
        return this.membersRealnameDao.queryPager(query);
    }

    /**
     * 新增数据
     *
     * @param membersRealname 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(MembersRealname membersRealname) {
        return this.membersRealnameDao.insert(membersRealname);
    }

    /**
     * 修改数据
     *
     * @param membersRealname 实例对象
     * @return 实例对象
     */
    @Override
    public int update(MembersRealname membersRealname) {
        return this.membersRealnameDao.update(membersRealname);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public int deleteById(Long id) {
        return this.membersRealnameDao.deleteById(id);
    }
}