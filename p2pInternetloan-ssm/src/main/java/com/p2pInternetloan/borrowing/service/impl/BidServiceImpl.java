package com.p2pInternetloan.borrowing.service.impl;

import com.p2pInternetloan.borrowing.entity.Bid;
import com.p2pInternetloan.borrowing.dao.BidDao;
import com.p2pInternetloan.borrowing.service.BidService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import com.p2pInternetloan.base.utils.Query;

/**
 * (Bid)表服务实现类
 *
 * @author cpc
 * @since 2019-10-23 15:03:53
 */
@Service("bidService")
public class BidServiceImpl implements BidService {
    @Resource
    private BidDao bidDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Bid queryById(Long id) {
        return this.bidDao.queryById(id);
    }

    /**
     * 通过query对象查询
     *
     * @param  query 分页查询对象 
     * @return 对象列表
     */
    @Override
    public List<Bid> queryPager(Query query) {
        return this.bidDao.queryPager(query);
    }

    /**
     * 新增数据
     *
     * @param bid 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(Bid bid) {
        return this.bidDao.insert(bid);
    }

    /**
     * 修改数据
     *
     * @param bid 实例对象
     * @return 实例对象
     */
    @Override
    public int update(Bid bid) {
        return this.bidDao.update(bid);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public int deleteById(Long id) {
        return this.bidDao.deleteById(id);
    }
}