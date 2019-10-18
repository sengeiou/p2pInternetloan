package com.p2pInternetloan.sys.controller;

import com.p2pInternetloan.base.utils.PageUtils;
import com.p2pInternetloan.base.utils.Query;
import com.p2pInternetloan.sys.entity.User;
import com.p2pInternetloan.sys.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2019-10-17 16:33:34
 */
@RestController
@Api(tags="用户信息请求类")
@RequestMapping("user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public User selectOne(Integer id) {
        return this.userService.queryById(id);
    }


    /**
     *测试分页查询
     */
    @GetMapping("queryPage")
    @ApiOperation(value = "queryPage", notes = "用户分页查询", httpMethod = "GET")
    public PageUtils queryPage(@RequestParam Map<String, Object> params){
        // 查询列表数据
        Query query = new Query(params);
        List<User> list =this.userService.queryPager(query);
        return new PageUtils(list, query.getTotal());
    }



}

