package com.zhongzhou.common.utils;


import org.apache.shiro.crypto.hash.SimpleHash;

import java.io.Serializable;

/**
 * @Classname SHA256Util
 * @Description Sha-256加密工具
 * @Date 2020/2/20 19:46
 * @Author wj
 */
public class SHA256Util implements Serializable {
    private static final long serialVersionUID = 427465244036621179L;

    /**
     * 私有构造器
     */
    private SHA256Util() {
    }

    /**
     * 加密算法
     */
    public final static String HASH_ALGORITHM_NAME = "SHA-256";
    /**
     * 循环次数
     */
    public final static int HASH_ITERATIONS = 15;

    /**
     * 执行加密-采用SHA256和盐值加密
     *
     * @param password String 密码
     * @param salt     String 盐值
     * @return String 密文
     */
    public static String sha256(String password, String salt) {
        return new SimpleHash(HASH_ALGORITHM_NAME, password, salt, HASH_ITERATIONS).toString();
    }
}
