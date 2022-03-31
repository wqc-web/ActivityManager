package com.zhongzhou.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhongzhou.api.entity.ActivityUser;
import com.zhongzhou.api.service.impl.ActivityUserServiceImpl;
import com.zhongzhou.common.base.BaseController;
import com.zhongzhou.common.base.Pager;
import com.zhongzhou.common.bean.ReturnEntity;
import com.zhongzhou.common.bean.ReturnEntityError;
import com.zhongzhou.common.bean.ReturnEntitySuccess;
import com.zhongzhou.common.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 活动用户
 * </p>
 *
 * @author wqc
 * @since 2021-05-24
 */
@RestController
@RequestMapping("/api/activityUser")
@Slf4j
public class ActivityUserController extends BaseController {

    @Resource
    private ActivityUserServiceImpl activityUserService;

    /**
     * 分页查询列表
     *
     * @param pager        分页信息
     * @param activityUser ActivityUser
     * @return ReturnEntity
     */
    @GetMapping("/page")
    public ReturnEntity selectPageList(Pager<ActivityUser> pager, ActivityUser activityUser,
                                       HttpServletRequest request, HttpServletResponse response) {
        try {
            QueryWrapper<ActivityUser> wrapper = new QueryWrapper<>();
            List<ActivityUser> records = activityUserService.page(pager, wrapper).getRecords();
            int count = activityUserService.count(wrapper);
            return new ReturnEntitySuccess(Constants.MSG_FIND_SUCCESS, count, records);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[" + Constants.MSG_FIND_FAILED + "]:" + e.getMessage());
            return new ReturnEntityError(Constants.MSG_FIND_FAILED, null, null);
        }
    }

    /**
     * 查询所有列表
     *
     * @param activityUser ActivityUser
     * @return ReturnEntity
     */
    @GetMapping("/list")
    public ReturnEntity selectList(ActivityUser activityUser,
                                   HttpServletRequest request, HttpServletResponse response) {
        try {
            QueryWrapper<ActivityUser> wrapper = new QueryWrapper<>();
            List<ActivityUser> list = activityUserService.list(wrapper);
            return new ReturnEntitySuccess(Constants.MSG_FIND_SUCCESS, list.size(), list);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[" + Constants.MSG_FIND_FAILED + "]:" + e.getMessage());
            return new ReturnEntityError(Constants.MSG_FIND_FAILED, null, null);
        }
    }

    /**
     * 查询详情
     *
     * @param id 主键
     * @return ReturnEntity
     */
    @GetMapping("/detail/{id}")
    public ReturnEntity selectById(@PathVariable("id") Long id) {
        try {
            ActivityUser activityUser = activityUserService.getById(id);
            if (null != activityUser) {
                return new ReturnEntitySuccess(Constants.MSG_FIND_SUCCESS, activityUser);
            } else {
                return new ReturnEntitySuccess(Constants.MSG_FIND_NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[id:{} " + Constants.MSG_FIND_FAILED + "]:{}", id, e.getMessage());
            return new ReturnEntityError(Constants.MSG_FIND_FAILED);
        }
    }

    /**
     * 新增
     *
     * @param activityUser ActivityUser
     * @param result       BindingResult
     * @return ReturnEntity
     */
    @PostMapping("/add")
    public ReturnEntity save(@Validated @RequestBody ActivityUser activityUser, BindingResult result,
                             HttpServletRequest request, HttpServletResponse response) {
        if (result.hasErrors()) {
            FieldError fieldError = result.getFieldErrors().get(0);
            String errorMsg = fieldError.getDefaultMessage();
            if (Constants.MSG_ERROR_CANNOT_NULL.equals(errorMsg)) {
                errorMsg = fieldError.getField() + fieldError.getDefaultMessage();
            }
            return new ReturnEntityError(errorMsg, null, activityUser);
        } else {
            try {
                QueryWrapper<ActivityUser> wrapper = new QueryWrapper<>();
                if (activityUserService.count(wrapper) > 0) {
                    return new ReturnEntityError(Constants.MSG_FIND_EXISTED, activityUser);
                } else {
                    if (activityUserService.save(activityUser)) {
                        return new ReturnEntitySuccess(Constants.MSG_INSERT_SUCCESS, activityUser);
                    } else {
                        return new ReturnEntityError(Constants.MSG_INSERT_FAILED, activityUser);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("[" + Constants.MSG_INSERT_FAILED + "]:{}", e.getMessage());
                return new ReturnEntityError(Constants.MSG_INSERT_FAILED, activityUser);
            }
        }
    }

    /**
     * 修改
     *
     * @param id           主键
     * @param activityUser ActivityUser
     * @param result       BindingResult
     * @return ReturnEntity
     */
    @PutMapping("/edit/{id}")
    public ReturnEntity updateById(@PathVariable("id") Long id, @Validated @RequestBody ActivityUser activityUser, BindingResult result,
                                   HttpServletRequest request, HttpServletResponse response) {
        if (result.hasErrors()) {
            return new ReturnEntityError(result.getFieldErrors().get(0).getDefaultMessage(), activityUser);
        } else {
            try {
                if (null == activityUserService.getById(id)) {
                    return new ReturnEntityError(Constants.MSG_FIND_NOT_FOUND, activityUser);
                } else {
                    if (activityUserService.updateById(activityUser)) {
                        return new ReturnEntitySuccess(Constants.MSG_UPDATE_SUCCESS, activityUser);
                    } else {
                        return new ReturnEntityError(Constants.MSG_UPDATE_FAILED, activityUser);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("[id:{} " + Constants.MSG_UPDATE_FAILED + "]:{}", id, e.getMessage());
                return new ReturnEntityError(Constants.MSG_UPDATE_FAILED, activityUser);
            }
        }
    }

    /**
     * 删除
     *
     * @param id 主键
     * @return ReturnEntity
     */
    @DeleteMapping("/delete/{id}")
    public ReturnEntity deleteById(@PathVariable("id") Long id) {
        try {
            if (null == activityUserService.getById(id)) {
                return new ReturnEntityError(Constants.MSG_FIND_NOT_FOUND, id);
            } else {
                if (activityUserService.removeById(id)) {
                    return new ReturnEntitySuccess(Constants.MSG_DELETE_SUCCESS, id);
                } else {
                    return new ReturnEntityError(Constants.MSG_DELETE_FAILED, id);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[id:{} " + Constants.MSG_DELETE_FAILED + "]:{}", id, e.getMessage());
            return new ReturnEntityError(Constants.MSG_DELETE_FAILED, id);
        }
    }

}
