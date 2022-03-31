package com.zhongzhou.common.utils;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName WecatUtils
 * @Description 微信公众号相关工具类
 * @Date 2020/3/12 9:21
 * @Author wj
 */
public class WecatUtil implements Serializable {
    private static final long serialVersionUID = 9043035757655441920L;

    public static boolean checkSignature(String signature, String timestamp, String nonce) {
        try {
            //将token、timestamp、nonce三个参数字典排序
            List<String> list = new ArrayList<>();
            list.add(WeCatConstants.WX_TOKEN);
            list.add(timestamp);
            list.add(nonce);
            Collections.sort(list);
            StringBuffer sb = new StringBuffer();
            list.forEach(sb::append);
            //sha1加密
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] digest = md.digest(sb.toString().getBytes());
            String sign = new String(digest);
            return sign.equals(signature);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        }
    }
}
