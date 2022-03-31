package com.zhongzhou.common.utils;

import java.io.Serializable;

/**
 * @ClassName WeCatConstants
 * @Description 公众号相关常量
 * @Date 2020/3/12 10:50
 * @Author wj
 */
public class WeCatConstants implements Serializable {
    private static final long serialVersionUID = 8627153829655249114L;
    /**
     * 公众号token
     */
    public static final String WX_TOKEN = "wjdev";
    /**
     * 公众号appId
     */
    public static final String WX_APP_ID = "wx6d52be737ceffaa3";
    /**
     * 公众号AppSecret
     */
    public static final String WX_APP_SECRET = "d23a05d5594b6ca18ba3d0d2cfe037de";
    /**
     * 获取access_token的授权类型
     */
    public static final String WX_GRANT_TYPE = "client_credential";
    /**
     * 获取access_token的URL
     */
    public static final String WX_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";
    /**
     * 获取openid的URL
     */
    public static final String WX_OPENID_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";
    /**
     * 获取openid的授权类型
     */
    public static final String WX_OPENID_GRANT_TYPE = "authorization_code";
    /**
     * 获取用户信息的URL
     */
    public static final String WX_USER_INFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info";
    /**
     * 获取页面用户信息的URL
     */
    public static final String WX_PAGE_USER_INFO_URL = "https://api.weixin.qq.com/sns/userinfo";

}
