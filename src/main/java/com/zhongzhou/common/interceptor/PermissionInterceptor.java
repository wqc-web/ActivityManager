package com.zhongzhou.common.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.zhongzhou.api.common.TokenController;
import com.zhongzhou.api.entity.OperationLog;
import com.zhongzhou.api.entity.Permission;
import com.zhongzhou.api.entity.SysUser;
import com.zhongzhou.api.mapper.PermissionMapper;
import com.zhongzhou.api.service.impl.OperationLogServiceImpl;
import com.zhongzhou.api.service.impl.SysUserServiceImpl;
import com.zhongzhou.common.bean.ReturnEntityError;
import com.zhongzhou.common.utils.Constants;
import com.zhongzhou.common.utils.IpUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName PermissionInterceptor
 * @Description 权限菜单拦截器
 * @Date 2020/3/8 17:46
 * @Author wj
 */
@Component
public class PermissionInterceptor implements HandlerInterceptor {
    /**
     * token即将到期时间
     */
    private final static Long EXPIRETIME = 300L;

    @Resource
    private TokenController tokenController;
    @Resource
    private SysUserServiceImpl sysUserService;
    @Resource
    private OperationLogServiceImpl opeartionLogService;
    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("被拦截啦！！！！！！！！！！");
        String uri = request.getRequestURI();
        System.out.println("uri=" + uri);
        String authorization = request.getHeader("Authorization");
        System.out.println("authorization=" + authorization);
        String method = request.getMethod();
        System.out.println("method=" + method);

        boolean authFlag = false;
        if (StringUtils.isNotBlank(authorization)) {
            try {
                if (tokenController.isNotBlank(request, response)) {
                    if (tokenController.getExpire(request, response) < EXPIRETIME) {
                        tokenController.updateExpire(request, response);
                    }
                    Long userId = tokenController.getUserId(request, response);
                    if (null != userId) {
                        String url = uri.replace("/example", "");
                        System.out.println("url=" + url);
                        SysUser user = sysUserService.getById(userId);
                        List<Permission> permissions = permissionMapper.findListBySysUserId(userId);
                        for (Permission permission : permissions) {
                            if (StringUtils.isNotBlank(permission.getUrl())) {
                                if (url.contains(permission.getUrl())) {
                                    authFlag = true;
                                    OperationLog operationLog = new OperationLog();
                                    operationLog.setName(permission.getName());
                                    operationLog.setUrl(url);
                                    operationLog.setParameters(request.getQueryString());
                                    String remoteAddr = IpUtil.getIpAddr(request);
                                    operationLog.setIpAddr(remoteAddr);
                                    operationLog.setOperationUserId(user.getId());
                                    operationLog.setOperationUserName(user.getUserName());
                                    operationLog.setOperationTime(LocalDateTime.now());
                                    opeartionLogService.save(operationLog);
                                    break;
                                }
                            }
                        }
                        if (!authFlag) {
                            PrintWriter writer = response.getWriter();
                            //设置编码为UTF-8
                            response.setCharacterEncoding("utf-8");
                            response.setContentType("text/html; charset=utf-8");
                            //403--权限不足，请联系管理员
                            writer.append(JSONObject.toJSONString(new ReturnEntityError(Constants.CODE_FORBIDDEN, Constants.MSG_FORBIDDEN)));
                        }
                    } else {
                        PrintWriter writer = response.getWriter();
                        //设置编码为UTF-8
                        response.setCharacterEncoding("utf-8");
                        response.setContentType("text/html; charset=utf-8");
                        //1202--用户名不存在
                        writer.append(JSONObject.toJSONString(new ReturnEntityError(Constants.CODE_LOGIN_USERNAME_ERROR, Constants.MSG_LOGIN_USERNAME_ERROR)));
                    }
                } else {
                    PrintWriter writer = response.getWriter();
                    //设置编码为UTF-8
                    response.setCharacterEncoding("utf-8");
                    response.setContentType("text/html; charset=utf-8");
                    //1001--未登录或已过期
                    writer.append(JSONObject.toJSONString(new ReturnEntityError(Constants.CODE_TOKEN_NOT_FOUND, Constants.MSG_TOKEN_NOT_FOUND)));
                }
            } catch (IOException e) {
                e.printStackTrace();
                PrintWriter writer = response.getWriter();
                //设置编码为UTF-8
                response.setCharacterEncoding("utf-8");
                response.setContentType("text/html; charset=utf-8");
                //1001--未登录或已过期
                writer.append(JSONObject.toJSONString(new ReturnEntityError(Constants.CODE_TOKEN_NOT_FOUND, Constants.MSG_TOKEN_NOT_FOUND)));
            }
        } else {
            PrintWriter writer = response.getWriter();
            //设置编码为UTF-8
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html; charset=utf-8");
            //1001--未登录或已过期
            writer.append(JSONObject.toJSONString(new ReturnEntityError(Constants.CODE_TOKEN_NOT_FOUND, Constants.MSG_TOKEN_NOT_FOUND)));
            authFlag = false;
        }
        return authFlag;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
