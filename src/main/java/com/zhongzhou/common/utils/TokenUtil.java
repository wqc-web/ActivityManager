package com.zhongzhou.common.utils;

import com.zhongzhou.common.base.Base58Util;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * @ClassName: Md5Util
 * @Description: 管理token
 * @Author: wj
 * @Date: 2020-03-08 18:13:35
 **/
@Component
public class TokenUtil implements Serializable {
    private static final long serialVersionUID = 1896646223429064513L;

//    public static String getToken(User user, HttpServletRequest request, HttpServletResponse response) {
//        return Base58Util.getIDCode();
//    }

}
