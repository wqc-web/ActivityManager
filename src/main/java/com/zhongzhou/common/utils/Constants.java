package com.zhongzhou.common.utils;

import ch.qos.logback.classic.util.LogbackMDCAdapter;
import com.zhongzhou.api.entity.Attachment;
import com.zhongzhou.api.entity.DictionaryType;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Constants
 * @Description 魔法值常量
 * @Date 2020/3/8 17:11
 * @Author wj
 */
public class Constants implements Serializable {
    private static final long serialVersionUID = -7153088581645065553L;

    /**
     * 域名
     */
    public static final String DOMAIN_NAME = "http://jszzkj.cn";
    /**
     * web项目名称
     */
    public static final String WEB_PROJECT_NAME = "ActivityManager";
    /**
     * mobile名称
     */
    public static final String MOBILE_PROJECT_NAME = "ActivityMobile";


    /**
     * Content-Type：application/json; charset=utf-8
     */
    public static final String CONTENT_TYPE_JSON = "application/json; charset=utf-8";
    /**
     * 默认日期格式：yyyy-MM-dd HH:mm:ss
     */
    public static final String DATETIME_FORMATTER = "yyyy-MM-dd HH:mm:ss";
    /**
     * AES加密解密的password
     */
    public static final String AES_PASSWORD = "zz@jszzkj.cn";
    /**
     * 默认系统登录有效期
     */
    public static final long DEFAULT_EXPIRE_SECOND = 1800;
    /**
     * 文件src
     */
//    public static final String FILE_SRC = "http://localhost:8081/file";
    /**
     * 上传文件存放路径
     */
//    public static final String FILE_PATH = "/home/attachment/example/";
    /**
     * 成功code
     */
    public static final Integer CODE_SUCCESS = 0;
    /**
     * 成功msg
     */
    public static final String MSG_SUCCESS = "SUCCESS";
    /**
     * 错误code
     */
    public static final Integer CODE_ERROR = 1;
    /**
     * 错误msg
     */
    public static final String MSG_ERROR = "FAILED";

    /**
     * 未知异常code
     */
    public static final Integer CODE_UNKNOWN_ERROR_MSG = -1;

    /**
     * 状态：是
     */
    public static final int STATUS_YES = 1;

    /**
     * 状态：否
     */
    public static final int STATUS_NO = 0;

    /**
     * 未知异常msg
     */
    public static final String MSG_UNKNOWN_ERROR_MSG = "未知异常";
    public static final String MSG_ERROR_CANNOT_NULL = "不能为null";
    public static final String MSG_CONNECTION_ERROR = "连接异常";
    public static final String MSG_FIND_NOT_FOUND = "未查询到结果";
    public static final String MSG_FIND_EXISTED = "数据已存在";
    public static final String MSG_FIND_SUCCESS = "查询成功";
    public static final String MSG_FIND_FAILED = "查询失败";
    public static final String MSG_INSERT_SUCCESS = "新增成功";
    public static final String MSG_INSERT_FAILED = "新增失败";
    public static final String MSG_UPDATE_SUCCESS = "修改成功";
    public static final String MSG_UPDATE_FAILED = "修改失败";
    public static final String MSG_DELETE_SUCCESS = "删除成功";
    public static final String MSG_DELETE_FAILED = "删除失败";
    public static final String MSG_UPLOAD_SUCCESS = "上传成功";
    public static final String MSG_UPLOAD_FAILED = "上传失败";

    public static final Integer CODE_TOKEN_NOT_FOUND = 1001;
    public static final String MSG_TOKEN_NOT_FOUND = "未登录或已过期";
    public static final String MSG_LOGIN_SUCCESS = "登录成功";
    public static final String MSG_LOGOUT_SUCCESS = "登出成功";
    public static final Integer CODE_LOGIN_ERROR = 1201;
    public static final String MSG_LOGIN_ERROR = "登录失败";
    public static final String MSG_LOGOUT_ERROR = "登出失败";
    public static final Integer CODE_LOGIN_USERNAME_ERROR = 1202;
    public static final String MSG_LOGIN_USERNAME_ERROR = "用户名不存在";
    public static final Integer CODE_LOGIN_PASSWORD_ERROR = 1203;
    public static final String MSG_LOGIN_PASSWORD_ERROR = "用户名或密码错误";
    public static final String MSG_LOGIN_LOCKED_ERROR = "用户已被冻结";
    public static final String MSG_LOGIN_UNAUTH_ERROR = "未登录";


    public static final String MSG_GRANT_SUCCESS = "授权成功";
    public static final String MSG_GRANT_FAILED = "授权失败";
    public static final Integer CODE_FORBIDDEN = 403;
    public static final String MSG_FORBIDDEN = "权限不足，请联系管理员";
    public static final Integer CODE_LOCKED = 0;

    public static final Long NUM_ZERO = 0L;
    public static final Long NUM_TEN = 10L;

    public static final String ERROR_IDENTITY_VALIDATE = "身份证号码非法";
    public static final String ERROR_PHONE_VALIDATE = "电话号码非法";

    /**
     * 部门集合
     */
    public static Map<Long, String> MAP_DEPARTMENT = new HashMap<>();
    /**
     * 角色集合
     */
    public static Map<Long, String> MAP_ROLE = new HashMap<>();
    /**
     * 权限菜单集合
     */
    public static Map<Long, String> MAP_PERMISSION = new HashMap<>();
    /**
     * 字典类型集合
     */
    public static Map<Long, DictionaryType> MAP_DICTIONARY_TYPE = new HashMap<>();
    /**
     * 菜单图标附件集合
     */
    public static Map<Long, Attachment> MAP_MENU_ICON = new HashMap<>();
}
