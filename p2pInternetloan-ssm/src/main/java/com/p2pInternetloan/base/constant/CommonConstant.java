package com.p2pInternetloan.base.constant;

/**
 * @Description: 定义公共常量
 * @Author: cpc
 * @Date: 2019-10-19 11:33
 * @Version: V1.0
 */
public class CommonConstant {
    /* JWT_WEB_TTL：WEBAPP应用中token的有效时间,默认30分钟 */
    public static final long  JWT_WEB_TTL  = 30 * 60 * 1000;;

    /*  将jwt令牌保存到header中的key名称 用于权限认证*/
    public static final String JWT_HEADER_KEY = "Authorization";

    /*这是保存验证码颁发的jwt令牌*/
    public static final String JWT_VERIFICATION_KEY = "verificationJwt";

    /* 用户存入reids中的验证码前缀*/
    public static final String VERIFICATION_PREFIX = "verificationCode_";

   /* 用户登录成功或reids中的验证码*/
    public static final String PREFIX_USER_TOKEN  = "PREFIX_USER_TOKEN_";

    /**字典翻译文本后缀*/
    public static final String DICT_TEXT_SUFFIX = "_dictText";


}
