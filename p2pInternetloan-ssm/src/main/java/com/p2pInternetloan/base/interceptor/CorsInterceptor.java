package com.p2pInternetloan.base.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 解决跨域问题
 * @Author: cpc
 * @Date: 2019-10-15 22:18
 * @Version: V1.0
 */
@Slf4j//在类上方使用@Slf4j注解以后就可以不用创建logger对象，直接使用log即可
public class CorsInterceptor implements HandlerInterceptor {


    /**
     * 在调用方法前执行
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("进入跨域过滤器");
        // Access-Control-Allow-Origin就是我们需要设置的域名
        // Access-Control-Allow-Headers跨域允许包含的头。
        // Access-Control-Allow-Methods是允许的请求方式
        response.setHeader("Access-Control-Allow-Origin", "*");// *,任何域名
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE");

        //允许客户端发送一个新的请求头
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, jwt, verificationJwt");
        //允许客户端处理一个新的响应头jwt
        response.setHeader("Access-Control-Expose-Headers", "jwt");
        response.setHeader("Access-Control-Expose-Headers", "verificationJwt");

        //axios和ajax会发送两次请求，第一次提交方式为：option直接返回即可
        if("OPTIONS".equals(request.getMethod())) {
            return false;
        }
        return true;
    }
}
