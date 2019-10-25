package com.p2pInternetloan.setting.service.impl;

import com.p2pInternetloan.setting.entity.Setting;
import com.p2pInternetloan.setting.dao.SettingDao;
import com.p2pInternetloan.setting.service.SettingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import com.p2pInternetloan.base.utils.Query;

/**
 * (Setting)表服务实现类
 *
 * @author cpc
 * @since 2019-10-22 21:16:54
 */
@Service("settingService")
public class SettingServiceImpl implements SettingService {
    @Resource
    private SettingDao settingDao;

    /**
     * 通过ID查询单条数据
     *
     * @param key 主键
     * @return 实例对象
     */
    @Override
    public Setting queryById(String key) {
        return this.settingDao.queryById(key);
    }

    /**
     * 通过query对象查询
     *
     * @param  query 分页查询对象 
     * @return 对象列表
     */
    @Override
    public List<Setting> queryPager(Query query) {
        return this.settingDao.queryPager(query);
    }

    /**
     * 新增数据
     *
     * @param setting 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(Setting setting) {
        return this.settingDao.insert(setting);
    }

    /**
     * 修改数据
     *
     * @param setting 实例对象
     * @return 实例对象
     */
    @Override
    public int update(Setting setting) {
        return this.settingDao.update(setting);
    }

    /**
     * 通过主键删除数据
     *
     * @param key 主键
     * @return 是否成功
     */
    @Override
    public int deleteById(String key) {
        return this.settingDao.deleteById(key);
    }


}