package com.p2pInternetloan.sys.shiro;

import com.p2pInternetloan.base.constant.CommonConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 这是shiro的权限过滤器
 * @Author: cpc
 * @Date: 2019-10-18 20:09
 * @Version: V1.0
 */
@Slf4j
@SuppressWarnings("unchecked")
public class MyStatelessShiroFilter extends AccessControlFilter{


    /**
     * 返回false
     * @param servletRequest
     * @param servletResponse
     * @param o
     * @return 返回结果是false的时候才会执行下面的onAccessDenied方法
     * @throws Exception
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        log.info("is access allowed");
        return false;
    }

    /**
     * 从请求头获取token并验证，验证通过后交给realm进行登录
     * @param servletRequest
     * @param servletResponse
     * @return 返回结果为true表明登录通过
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        //这是获取 请求对象
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //获取获取保持在vue 端的jwt令牌
        String token = request.getHeader(CommonConstant.JWT_HEADER_KEY);
        //封装 token
        JwtToken jwtToken = new JwtToken(token);
        try {
            // 提交给realm进行登入，如果错误他会抛出异常并被捕获
            getSubject(request, response).login(jwtToken);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

}
