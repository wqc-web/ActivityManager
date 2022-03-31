package com.zhongzhou.api.common;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.zhongzhou.common.utils.Constants;
import com.zhongzhou.common.utils.JwtUtil;
import com.zhongzhou.common.utils.RedisUtil;
import com.zhongzhou.common.utils.WeCatConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.HashMap;

/**
 * @ClassName TokenController
 * @Description token工具类
 * @Date 2020/6/29 12:39
 * @Author wj
 */
@Component
@Slf4j
public class TokenController implements Serializable {
    private static final long serialVersionUID = 935258820629733612L;
    @Resource
    private RedisUtil redisUtil;

    /**
     * 判断token是否存在
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @return true:存在，false:不存在
     */
    public boolean isNotBlank(HttpServletRequest request, HttpServletResponse response) {
        String tokenCode = request.getHeader("Authorization");
        return redisUtil.isNotBlank(tokenCode);
    }

    /**
     * 根据key获取值
     *
     * @param key 键
     * @return 值
     */
    public String get(String key) {
        return redisUtil.get(key);
    }

    /**
     * 根据key和value赋值（永久）
     *
     * @param key   键
     * @param value 值
     */
    public void set(String key, String value) {
        redisUtil.set(key, value);
    }

    /**
     * 根据key和value赋值（期限）
     *
     * @param key        键
     * @param value      值
     * @param expireTime 到期时间
     */
    public void set(String key, String value, Long expireTime) {
        redisUtil.set(key, value, expireTime);
    }

    /**
     * 获取用户ID
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @return 用户ID
     */
    public Long getUserId(HttpServletRequest request, HttpServletResponse response) {
        return JwtUtil.verifyToken(request);
    }

    /**
     * 获取用户ID
     *
     * @param request HttpServletRequest
     * @return 用户ID
     */
    public Long getUserId(HttpServletRequest request) {
        return JwtUtil.verifyToken(request);
    }

    /**
     * 获取Redis剩余时间
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @return 剩余时间
     */
    public Long getExpire(HttpServletRequest request, HttpServletResponse response) {
        String tokenCode = request.getHeader("Authorization");
        if (redisUtil.isNotBlank(tokenCode)) {
            return redisUtil.ttl(tokenCode);
        } else {
            throw new RuntimeException("未找到token");
        }
    }

    /**
     * 更新Redis到期时间
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @return true:更新成功；false：更新失败
     */
    public boolean updateExpire(HttpServletRequest request, HttpServletResponse response) {
        String tokenCode = request.getHeader("Authorization");
        if (redisUtil.isNotBlank(tokenCode)) {
            redisUtil.expire(tokenCode, Constants.DEFAULT_EXPIRE_SECOND);
            return true;
        } else {
            throw new RuntimeException("未找到token");
        }
    }

    /**
     * 登出
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @return true：退出成功；false：退出失败
     */
    public boolean logout(HttpServletRequest request, HttpServletResponse response) {
        String tokenCode = request.getHeader("Authorization");
        if (redisUtil.isNotBlank(tokenCode)) {
            redisUtil.del(tokenCode);
            return true;
        } else {
            throw new RuntimeException("未找到token");
        }
    }

    /**
     * 获取微信AccessToken
     *
     * @return 微信AccessToken
     */
    public String getWxAccessToken() {
        String key = "wxAccessToken";
        //从redis获取微信AccessToken
        String wxAccessToken = redisUtil.get(key);
        //存在直接返回
        if (StringUtils.isNotBlank(wxAccessToken)) {
            return wxAccessToken;
        } else { //不存在就去获取
            try {
                HashMap<String, Object> paramMap = new HashMap<>();
                paramMap.put("grant_type", WeCatConstants.WX_GRANT_TYPE);
                paramMap.put("appid", WeCatConstants.WX_APP_ID);
                paramMap.put("secret", WeCatConstants.WX_APP_SECRET);
                String result = HttpUtil.get(WeCatConstants.WX_TOKEN_URL, paramMap);
                JSONObject json = JSONObject.parseObject(result);
                if (null != json) {
                    if (json.containsKey("access_token")) {
                        wxAccessToken = json.getString("access_token");
                        Long expiresTime = json.getLong("expires_in");
                        //redis存微信AccessToken
                        redisUtil.set(key, wxAccessToken, expiresTime);
                        return wxAccessToken;
                    } else {
                        throw new RuntimeException(json.toString());
                    }
                } else {
                    throw new RuntimeException("url result is null!");
                }
            } catch (Exception e) {
                throw new RuntimeException("get access_token error!");
            }
        }
    }

    /**
     * 获取微信用户openid
     *
     * @param code 票据
     * @return 微信用户openid
     */
    public JSONObject getWxPageJson(String code) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("appid", WeCatConstants.WX_APP_ID);
        paramMap.put("secret", WeCatConstants.WX_APP_SECRET);
        paramMap.put("code", code);
        paramMap.put("grant_type", WeCatConstants.WX_OPENID_GRANT_TYPE);
        String result = HttpUtil.get(WeCatConstants.WX_OPENID_URL, paramMap);
        JSONObject json = JSONObject.parseObject(result);
        if (null != json) {
            //打印微信用户信息
            log.info("打印微信用户信息:{}", result);
            //
            if (json.containsKey("openid")) {
                return json;
            } else {
                throw new RuntimeException(json.toString());
            }
        } else {
            throw new RuntimeException("url result is null!");
        }
    }

    /**
     * 获取微信用户信息
     *
     * @return 用户信息
     */
    public JSONObject getWxUserInfo(String accessToken, String openId) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("access_token", accessToken);
        paramMap.put("openid", openId);
        paramMap.put("lang", "zh_CN");
        String result = HttpUtil.get(WeCatConstants.WX_USER_INFO_URL, paramMap);
        JSONObject json = JSONObject.parseObject(result);
        if (null != json) {
            //打印微信用户信息
            log.info("打印微信用户信息:{}", result);
            //
            if (json.containsKey("openid")) {
                return json;
            } else {
                throw new RuntimeException(json.toString());
            }
        } else {
            throw new RuntimeException("url result is null!");
        }
    }

    /**
     * 获取页面微信用户信息
     *
     * @return 用户信息
     */
    public JSONObject getPageWxUserInfo(String accessToken, String openId) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("access_token", accessToken);
        paramMap.put("openid", openId);
        paramMap.put("lang", "zh_CN");
        String result = HttpUtil.get(WeCatConstants.WX_PAGE_USER_INFO_URL, paramMap);
        JSONObject json = JSONObject.parseObject(result);
        if (null != json) {
            //打印微信用户信息
            log.info("打印微信用户信息:{}", result);
            //
            if (json.containsKey("openid")) {
                return json;
            } else {
                throw new RuntimeException(json.toString());
            }
        } else {
            throw new RuntimeException("url result is null!");
        }
    }

}
