package com.p2pInternetloan.members.service.impl;

import com.p2pInternetloan.members.entity.MembersDetail;
import com.p2pInternetloan.members.dao.MembersDetailDao;
import com.p2pInternetloan.members.service.MembersDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import com.p2pInternetloan.base.utils.Query;

/**
 * (MembersDetail)表服务实现类
 *
 * @author cpc
 * @since 2019-10-26 16:09:20
 */
@Service("membersDetailService")
public class MembersDetailServiceImpl implements MembersDetailService {
    @Resource
    private MembersDetailDao membersDetailDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public MembersDetail queryById(Integer id) {

        return this.membersDetailDao.queryById(id);
    }

    /**
     * 通过query对象查询
     *
     * @param  query 分页查询对象 
     * @return 对象列表
     */
    @Override
    public List<MembersDetail> queryPager(Query query) {
        return this.membersDetailDao.queryPager(query);
    }

    /**
     * 新增数据
     *
     * @param membersDetail 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(MembersDetail membersDetail) {
        return this.membersDetailDao.insert(membersDetail);
    }

    /**
     * 修改数据
     *
     * @param membersDetail 实例对象
     * @return 实例对象
     */
    @Override
    public int update(MembersDetail membersDetail) {
        return this.membersDetailDao.update(membersDetail);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public int deleteById(Integer id) {
        return this.membersDetailDao.deleteById(id);
    }
}