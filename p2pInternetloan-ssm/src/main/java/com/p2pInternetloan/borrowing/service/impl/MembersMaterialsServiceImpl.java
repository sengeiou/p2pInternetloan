package com.p2pInternetloan.borrowing.service.impl;

import com.p2pInternetloan.borrowing.entity.MembersMaterials;
import com.p2pInternetloan.borrowing.dao.MembersMaterialsDao;
import com.p2pInternetloan.borrowing.service.MembersMaterialsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import com.p2pInternetloan.base.utils.Query;

/**
 * (MembersMaterials)表服务实现类
 *
 * @author cpc
 * @since 2019-10-23 15:03:16
 */
@Service("membersMaterialsService")
public class MembersMaterialsServiceImpl implements MembersMaterialsService {
    @Resource
    private MembersMaterialsDao membersMaterialsDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public MembersMaterials queryById(Long id) {
        return this.membersMaterialsDao.queryById(id);
    }

    /**
     * 通过query对象查询
     *
     * @param  query 分页查询对象 
     * @return 对象列表
     */
    @Override
    public List<MembersMaterials> queryPager(Query query) {
        return this.membersMaterialsDao.queryPager(query);
    }

    /**
     * 新增数据
     *
     * @param membersMaterials 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(MembersMaterials membersMaterials) {
        return this.membersMaterialsDao.insert(membersMaterials);
    }

    /**
     * 修改数据
     *
     * @param membersMaterials 实例对象
     * @return 实例对象
     */
    @Override
    public int update(MembersMaterials membersMaterials) {
        return this.membersMaterialsDao.update(membersMaterials);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public int deleteById(Long id) {
        return this.membersMaterialsDao.deleteById(id);
    }
}