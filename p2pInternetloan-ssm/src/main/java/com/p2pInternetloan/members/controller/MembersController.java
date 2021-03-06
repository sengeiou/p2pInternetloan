package com.p2pInternetloan.members.controller;

import com.p2pInternetloan.base.constant.CommonConstant;
import com.p2pInternetloan.base.utils.*;
import com.p2pInternetloan.members.entity.Members;
import com.p2pInternetloan.members.service.MembersService;
import com.p2pInternetloan.sys.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Members)表控制层
 *
 * @author cpc
 * @since 2019-10-25 16:10:56
 */
@RestController
@RequestMapping("members/")
public class MembersController {
    /**
     * 服务对象
     */
    @Resource
    private MembersService membersService;

    /**
     *redis模板
     */
    @Autowired(required = false)
    private RedisTemplate redisTemplate;

    /**
     * 这是redis工具类的注入
     */
    @Autowired(required = false)
    private RedisUtil redisUtil;

    /**
     * 分页查询
     *
     * @param  params 请求参数集
     * @return 结果集封装对象 
     */
    @GetMapping("queryPager")
    public  PageUtils queryPager(@RequestParam Map<String, Object> params) {
         Query query = new Query(params);
         List<Members> list = membersService.queryPager(query);
         return new PageUtils(list, query.getTotal());
    }

    /**
     * 用户登录
     * @return
     */
    @PostMapping("login")
    public R login(Members members, HttpServletRequest request, HttpServletResponse response){
//        //获取用户输入的验证码
//        String membersVerificationCode = request.getParameter("verificationCode");
//        //验证码是否正确
//        String membersJwt = request.getHeader(CommonConstant.JWT_VERIFICATION_KEY);
//        //获取到保存在redis中的验证码
//        Object redisVerificationCode =  redisTemplate.opsForValue().get(CommonConstant.VERIFICATION_PREFIX + membersJwt) ;
//        if(StringUtils.isEmpty(redisVerificationCode)){
//            return R.error("你的验证码已超时");
//        }
//        if(!redisVerificationCode.toString().equalsIgnoreCase(membersVerificationCode)){
//            return R.error("验证码错误");
//        }
        //登录验证
        Members loginObj = this.membersService.login(members);
        if(loginObj != null){
            //创建jwt验证成功的字符串
            Map map = new HashMap();
            //将用户名和密码保持到jwt字符串中去
            map.put("membersId",loginObj.getId());
            map.put("membersName", loginObj.getName());
            //生成jwt令牌
            String jwt = JwtUtils.createJwt(map, CommonConstant.JWT_WEB_TTL);
            //将用户名和密码放入到请求头域中（这里就前后台都一样了）
            response.setHeader(CommonConstant.JWT_HEADER_KEY, jwt);
            //将jwt令牌保持到 redis中（这里是客户专用的，在redsi中，方便以后做在线用户统计）
            redisUtil.set(CommonConstant.PREFIX_MEBERS_TOKEN + jwt, jwt);
            //这里设置失效时长
            redisUtil.expire(CommonConstant.PREFIX_MEBERS_TOKEN + jwt, JwtUtils.JWT_WEB_TTL / 1000);
            R r = R.ok("登录成功");
            //将用户对象返回给前端
            loginObj.setPassword("");
            r.put("data", loginObj);
            return r;
        }
        return R.error("登录失败");
    }
    /**
     * 用户注册
     * @return
     */
    @PostMapping("registered")
    public R registered(Members members, HttpServletRequest request, HttpServletResponse response){
        //获取用户输入的验证码
        String membersVerificationCode = request.getParameter("verificationCode");
        //验证码是否正确
        String membersJwt = request.getHeader(CommonConstant.JWT_VERIFICATION_KEY);
        //获取到保存在redis中的验证码
        Object redisVerificationCode =  redisTemplate.opsForValue().get(CommonConstant.VERIFICATION_PREFIX + membersJwt) ;
        if(StringUtils.isEmpty(redisVerificationCode)){
            return R.error("你的验证码已超时");
        }
        if(!redisVerificationCode.toString().equalsIgnoreCase(membersVerificationCode)){
            return R.error("验证码错误");
        }

        R r = R.update(this.membersService.insert(members));
        //初始化用户想干表的信息
        Integer maxId = this.membersService.getMaxId();
        this.membersService.initMembersDetail(maxId);
        this.membersService.initMembersAccount(maxId);
        return r;
    }


    /**
     * 这是退出登录的方法
     * @param request
     * @return
     */
    @PostMapping("logout")
    public R logout(HttpServletRequest request){
        //去redis中将数据给清空掉就ok了
        String toke = request.getHeader(CommonConstant.JWT_HEADER_KEY);
        //将redis中的数据给删掉就ok了
        redisTemplate.delete(CommonConstant.PREFIX_MEBERS_TOKEN  + toke);
        return R.ok();
    }


    /**
     * 获取当前登录的用户
     * @return
     */
    @GetMapping("getCurrentMembers")
    public Members getCurrentMembers(){
        return this.membersService.queryById(JwtSession.getCurrentMembersId());
    }



}