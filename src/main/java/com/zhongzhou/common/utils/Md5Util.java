package com.zhongzhou.common.utils;

import java.io.Serializable;

import static cn.hutool.crypto.digest.DigestUtil.md5Hex;

/**
 * @ClassName: Md5Util
 * @Description: Md5加盐加密与验证
 * @Author: wj
 * @Date: 2019-7-16 15:13
 **/
public class Md5Util implements Serializable {

    private static final long serialVersionUID = 7031742597826330763L;

    /**
     * md5加盐加密
     *
     * @param password
     * @return
     */
    public static String getSaltMD5(String password) {
//        // 生成一个16位的随机数
//        Random random = new Random();
//        StringBuilder sBuilder = new StringBuilder(16);
//        sBuilder.append(random.nextInt(99999999)).append(random.nextInt(99999999));
//        int len = sBuilder.length();
//        if (len < 16) {
//            for (int i = 0; i < 16 - len; i++) {
//                sBuilder.append("0");
//            }
//        }
//        // 生成最终的加密盐
//        String salt = sBuilder.toString();
        password = md5Hex(password);
//        char[] cs = new char[48];
//        for (int i = 0; i < 48; i += 3) {
//            cs[i] = password.charAt(i / 3 * 2);
//            char c = salt.charAt(i / 3);
//            cs[i + 1] = c;
//            cs[i + 2] = password.charAt(i / 3 * 2 + 1);
//        }
        return password;
    }

    /**
     * 验证加盐后是否和原文一致
     *
     * @param password
     * @param md5str
     * @return
     */
    public static boolean getSaltverifyMD5(String password, String md5str) {
//        char[] cs1 = new char[32];
//        char[] cs2 = new char[16];
//        for (int i = 0; i < 48; i += 3) {
//            cs1[i / 3 * 2] = md5str.charAt(i);
//            cs1[i / 3 * 2 + 1] = md5str.charAt(i + 2);
//            cs2[i / 3] = md5str.charAt(i + 1);
//        }
//        String salt = new String(cs2);
        return md5Hex(password).equals(md5str);
    }
}
