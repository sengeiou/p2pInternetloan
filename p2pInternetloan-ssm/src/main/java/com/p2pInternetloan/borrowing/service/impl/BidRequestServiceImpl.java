package com.p2pInternetloan.borrowing.service.impl;

import com.p2pInternetloan.borrowing.entity.BidRequest;
import com.p2pInternetloan.borrowing.dao.BidRequestDao;
import com.p2pInternetloan.borrowing.service.BidRequestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import com.p2pInternetloan.base.utils.Query;

/**
 * (BidRequest)表服务实现类
 *
 * @author cpc
 * @since 2019-10-25 11:14:57
 */
@Service("bidRequestService")
public class BidRequestServiceImpl implements BidRequestService {
    @Resource
    private BidRequestDao bidRequestDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public BidRequest queryById(Integer id) {
        return this.bidRequestDao.queryById(id);
    }

    /**
     * 通过query对象查询
     *
     * @param  query 分页查询对象 
     * @return 对象列表
     */
    @Override
    public List<BidRequest> queryPager(Query query) {
        return this.bidRequestDao.queryPager(query);
    }

    /**
     * 新增数据
     *
     * @param bidRequest 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(BidRequest bidRequest) {
        return this.bidRequestDao.insert(bidRequest);
    }

    /**
     * 修改数据
     *
     * @param bidRequest 实例对象
     * @return 实例对象
     */
    @Override
    public int update(BidRequest bidRequest) {
        return this.bidRequestDao.update(bidRequest);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public int deleteById(Integer id) {
        return this.bidRequestDao.deleteById(id);
    }
}