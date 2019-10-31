package com.p2pInternetloan.base.controller;

import com.p2pInternetloan.base.constant.CommonConstant;
import com.p2pInternetloan.base.utils.ImageUtil;
import com.p2pInternetloan.base.utils.VerifyCodeUtil;
import com.p2pInternetloan.sys.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 这是基础请求类 提供公共的请求方法
 * @Author: cpc
 * @Date: 2019-10-30 11:32
 * @Version: V1.0
 */
@RestController
@RequestMapping("/base/basecontroller")
public class BaseController {


    /**
     *redis模板
     */
    @Autowired(required = false)
    private RedisTemplate redisTemplate;


    /**
     * 生成验证码图片
     * @param req
     * @param resp
     * @return
     * @throws IOException
     */
    @GetMapping("verificationCode")
    @RequiresGuest
    @ApiOperation(value = "生成图片验证码", notes = "生成登录的时候的图片验证码，验证码格式是base64字符串")
    public String verificationCode(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //生成验证码随机数
        String word = VerifyCodeUtil.produceNumAndChar(4);
//        获取用户的jwt令牌
        String userVerificationJwt = req.getHeader(CommonConstant.JWT_VERIFICATION_KEY);
        //验证码令牌
        Claims claims = JwtUtils.validateJwtToken(userVerificationJwt);
        if(claims == null){
            //如果用户令牌过期那么对应存放在redis中的数据也要清空
            if(!StringUtils.isEmpty(userVerificationJwt)){
                redisTemplate.expire(CommonConstant.VERIFICATION_PREFIX + userVerificationJwt, 1, TimeUnit.DAYS);
            }
            userVerificationJwt =  JwtUtils.createJwt(new HashMap<String, Object>() ,JwtUtils.JWT_WEB_TTL);
            //将jwt令牌放入 response head中
            resp.setHeader(CommonConstant.JWT_VERIFICATION_KEY, userVerificationJwt);
        }
        //刷新缓存，更新验证码
        redisTemplate.opsForValue().set(CommonConstant.VERIFICATION_PREFIX  + userVerificationJwt , word,60, TimeUnit.SECONDS);
        //生成图片
        String code = "data:image/png;base64," + ImageUtil.createImageWithVerifyCode(word, 116,40);;
        return code;
    }

}
