package com.zhongzhou.api.common;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhongzhou.api.entity.Activity;
import com.zhongzhou.api.entity.SysUser;
import com.zhongzhou.api.service.impl.ActivityServiceImpl;
import com.zhongzhou.api.service.impl.ActivityUserServiceImpl;
import com.zhongzhou.api.service.impl.SysUserServiceImpl;
import com.zhongzhou.common.base.BaseController;
import com.zhongzhou.common.bean.ReturnEntity;
import com.zhongzhou.common.bean.ReturnEntityError;
import com.zhongzhou.common.bean.ReturnEntitySuccess;
import com.zhongzhou.common.utils.Constants;
import com.zhongzhou.common.utils.JwtUtil;
import com.zhongzhou.common.utils.Md5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author wj
 * @ClassName LoginController
 * @Description 登录与退出
 * @date 2020-06-29 12:19:39
 **/
@RestController
@RequestMapping("/api")
@Slf4j
public class LoginController extends BaseController {
    private static final long serialVersionUID = 7058712925721353762L;

    @Resource
    private SysUserServiceImpl sysUserService;
    @Resource
    private ActivityServiceImpl activityService;
    @Resource
    private ActivityUserServiceImpl activityUserService;


    /**
     * 用户登录
     *
     * @param userName     用户名
     * @param userPassword 密码
     * @param request      HttpServletRequest
     * @param response     HttpServletResponse
     * @return ReturnEntity
     */
    @PostMapping("/login")
    public ReturnEntity login(@RequestParam("userName") String userName, @RequestParam("userPassword") String userPassword,
                              HttpServletRequest request, HttpServletResponse response) {
        try {
            String contextPath = request.getContextPath();
            System.out.println("contextPath=" + contextPath);
            QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
            wrapper.eq("user_name", userName);
            SysUser sysUser = sysUserService.getOne(wrapper);
            if (null != sysUser) {
                if (Md5Util.getSaltverifyMD5(userPassword, sysUser.getUserPassword())) {
                    String authorization = JwtUtil.generateToken(sysUser.getId().toString());
                    tokenController.set(authorization, String.valueOf(sysUser.getId()), Constants.DEFAULT_EXPIRE_SECOND);
                    return new ReturnEntitySuccess(Constants.CODE_SUCCESS, Constants.MSG_LOGIN_SUCCESS, null, authorization);
                } else {
                    return new ReturnEntityError(Constants.CODE_LOGIN_PASSWORD_ERROR, Constants.MSG_LOGIN_PASSWORD_ERROR);
                }
            } else {
                return new ReturnEntityError(Constants.CODE_LOGIN_USERNAME_ERROR, Constants.MSG_LOGIN_USERNAME_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[" + Constants.CODE_LOGIN_ERROR + "]:{}", e.getMessage());
            return new ReturnEntityError(Constants.CODE_LOGIN_ERROR, Constants.MSG_LOGIN_ERROR);
        }
    }

    /**
     * 获取登录信息
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @return ReturnEntity
     */
    @GetMapping("/getLoginInfo")
    public ReturnEntity getLoginInfo(HttpServletRequest request, HttpServletResponse response) {
        try {
            if (tokenController.isNotBlank(request, response)) {
                SysUser sysUser = sysUserService.findDetailById(tokenController.getUserId(request, response));
                if (null != sysUser) {
                    return new ReturnEntitySuccess(Constants.MSG_FIND_SUCCESS, sysUser);
                } else {
                    return new ReturnEntityError(1001, Constants.MSG_FIND_NOT_FOUND);
                }
            } else {
                return new ReturnEntityError(1001, Constants.MSG_TOKEN_NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnEntityError(1001, Constants.MSG_TOKEN_NOT_FOUND);
        }
    }

    /**
     * 用户退出
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @return ReturnEntity
     */
    @PostMapping("/logout")
    public ReturnEntity logout(HttpServletRequest request, HttpServletResponse response) {
        try {
            if (tokenController.logout(request, response)) {
                return new ReturnEntitySuccess(1001, Constants.MSG_LOGOUT_SUCCESS);
            } else {
                return new ReturnEntityError(Constants.MSG_LOGOUT_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[" + Constants.MSG_LOGOUT_ERROR + "]:{}", e.getMessage());
            return new ReturnEntityError(Constants.MSG_LOGOUT_ERROR);
        }
    }

    /**
     * 微信获取登录信息
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @return ReturnEntity
     */
    @GetMapping("/wxLogin")
    public ReturnEntity wxLogin(HttpServletRequest request, HttpServletResponse response) {
        try {
            //获取微信AccessToken
//            String wxAccessToken = tokenController.getWxAccessToken();
            //
            String code = request.getParameter("code");
            //获取微信用户openid
            JSONObject wxPageJson = tokenController.getWxPageJson(code);
            String wxOpenid = wxPageJson.getString("openid");
            String wxAccessToken = wxPageJson.getString("access_token");
            //查询openid是否存在
            QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
            wrapper.eq("open_id", wxOpenid);
            List<SysUser> userList = sysUserService.list(wrapper);
            //存在用户
            if (userList != null && userList.size() > 0) {
                SysUser sysUser = userList.get(0);
                return new ReturnEntitySuccess(Constants.MSG_FIND_SUCCESS, sysUser);
            } else { //不存在
                //获取微信用户信息
                JSONObject wxUserInfo = tokenController.getPageWxUserInfo(wxAccessToken, wxOpenid);
                String nickname = wxUserInfo.getString("nickname");
                String headimgurl = wxUserInfo.getString("headimgurl");
                SysUser sysUser = new SysUser();
                sysUser.setOpenId(wxOpenid);
                sysUser.setUserName(nickname);
                sysUser.setHeadImg(headimgurl);
                sysUser.setCreateUserId(1L);
                sysUser.setCreateTime(LocalDateTime.now());
                //添加用户
                if (sysUserService.save(sysUser)) {
                    return new ReturnEntitySuccess(Constants.MSG_FIND_SUCCESS, sysUser);
                } else { //用户添加失败
                    return new ReturnEntityError("微信新用户添加失败", sysUser);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnEntityError(e.getMessage());
        }
    }


    /**
     * 微信扫码跳转活动
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @return ReturnEntity
     */
    @GetMapping("/wxJumpActivity")
    public ReturnEntity wxJumpActivity(HttpServletRequest request, HttpServletResponse response) {
        try {
            //获取微信AccessToken
//            String wxAccessToken = tokenController.getWxAccessToken();
            //
            String code = request.getParameter("code");
            //
            String state = request.getParameter("state");
            //获取微信用户openid
            JSONObject wxPageJson = tokenController.getWxPageJson(code);
            String wxOpenid = wxPageJson.getString("openid");
            String wxAccessToken = wxPageJson.getString("access_token");
            //查询openid对应的用户是否存在
            QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
            wrapper.eq("open_id", wxOpenid);
            List<SysUser> userList = sysUserService.list(wrapper);
            //
            SysUser sysUser = null;
            //存在用户
            if (userList != null && userList.size() > 0) {
                sysUser = userList.get(0);
            } else { //不存在
                //获取微信用户信息
                JSONObject wxUserInfo = tokenController.getPageWxUserInfo(wxAccessToken, wxOpenid);
                String nickname = wxUserInfo.getString("nickname");
                String headimgurl = wxUserInfo.getString("headimgurl");
                sysUser = new SysUser();
                sysUser.setOpenId(wxOpenid);
                sysUser.setUserName(nickname);
                sysUser.setHeadImg(headimgurl);
                sysUser.setCreateUserId(1L);
                sysUser.setCreateTime(LocalDateTime.now());
                //用户添加失败
                if (!sysUserService.save(sysUser)) {
                    return new ReturnEntityError("微信新用户添加失败", sysUser);
                }
            }
            //查询是否加入对应活动
            Activity activity = activityService.getById(Long.valueOf(state));
            //活动不存在
            if (activity == null) {
                return new ReturnEntityError("活动不存在");
            }
            //1加入，0未加入
            int existFlag = 0;
            //用户是否存在该活动
            if (activityUserService.existActivityUser(activity.getId(), sysUser.getId())) {
                existFlag = 1;
            }
            //跳转到活动页面
            response.sendRedirect(Constants.DOMAIN_NAME + "/" + Constants.MOBILE_PROJECT_NAME + "/index.html?userId=" + sysUser.getId() + "&activityId=" + state + "&existFlag=" + existFlag);
            //success end
            return new ReturnEntitySuccess(Constants.MSG_FIND_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnEntityError(e.getMessage());
        }
    }

}
