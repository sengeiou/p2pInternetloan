package com.p2pInternetloan.borrowing.service.impl;

import com.p2pInternetloan.borrowing.entity.ReturnDetail;
import com.p2pInternetloan.borrowing.dao.ReturnDetailDao;
import com.p2pInternetloan.borrowing.service.ReturnDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import com.p2pInternetloan.base.utils.Query;

/**
 * (ReturnDetail)表服务实现类
 *
 * @author cpc
 * @since 2019-10-31 14:03:43
 */
@Service("returnDetailService")
public class ReturnDetailServiceImpl implements ReturnDetailService {
    @Resource
    private ReturnDetailDao returnDetailDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ReturnDetail queryById(Integer id) {
        return this.returnDetailDao.queryById(id);
    }

    /**
     * 通过query对象查询
     *
     * @param  query 分页查询对象 
     * @return 对象列表
     */
    @Override
    public List<ReturnDetail> queryPager(Query query) {
        return this.returnDetailDao.queryPager(query);
    }

    /**
     * 新增数据
     *
     * @param returnDetail 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(ReturnDetail returnDetail) {
        return this.returnDetailDao.insert(returnDetail);
    }

    /**
     * 修改数据
     *
     * @param returnDetail 实例对象
     * @return 实例对象
     */
    @Override
    public int update(ReturnDetail returnDetail) {
        return this.returnDetailDao.update(returnDetail);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public int deleteById(Integer id) {
        return this.returnDetailDao.deleteById(id);
    }
}