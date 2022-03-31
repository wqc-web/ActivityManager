package com.zhongzhou.api.controller;


    import org.springframework.web.bind.annotation.*;
    import com.zhongzhou.common.base.BaseController;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhongzhou.common.bean.ReturnEntity;
import com.zhongzhou.common.bean.ReturnEntityError;
import com.zhongzhou.common.bean.ReturnEntitySuccess;
import com.zhongzhou.common.utils.Constants;
import com.zhongzhou.common.base.Pager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.zhongzhou.api.service.impl.UserDepartmentServiceImpl;
import com.zhongzhou.api.entity.UserDepartment;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;
/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wj
 * @since 2020-06-29
 */
@RestController
@RequestMapping("/api/userDepartment")
@Slf4j
public class UserDepartmentController extends BaseController {
    @Resource
    private UserDepartmentServiceImpl userDepartmentService;

    /**
     * 分页查询列表
     *
     * @param pager 分页信息
     * @param userDepartment UserDepartment
     * @return ReturnEntity
     */
    @GetMapping("/page")
    public ReturnEntity selectPageList(Pager<UserDepartment> pager, UserDepartment userDepartment,
        HttpServletRequest request,HttpServletResponse response){
        try{
            QueryWrapper<UserDepartment> wrapper=new QueryWrapper<>();
            List<UserDepartment> records= userDepartmentService.page(pager,wrapper).getRecords();
            int count= userDepartmentService.count(wrapper);
            return new ReturnEntitySuccess(Constants.MSG_FIND_SUCCESS,count,records);
        }catch(Exception e){
            e.printStackTrace();
            log.error("["+Constants.MSG_FIND_FAILED+"]:"+e.getMessage());
            return new ReturnEntityError(Constants.MSG_FIND_FAILED,null,null);
        }
    }

    /**
     * 查询所有列表
     * @param userDepartment UserDepartment
     * @return ReturnEntity
     */
    @GetMapping("/list")
    public ReturnEntity selectList(UserDepartment userDepartment,
        HttpServletRequest request,HttpServletResponse response){
        try{
            QueryWrapper<UserDepartment> wrapper=new QueryWrapper<>();
            List<UserDepartment> list= userDepartmentService.list(wrapper);
            return new ReturnEntitySuccess(Constants.MSG_FIND_SUCCESS,list.size(),list);
        }catch(Exception e){
            e.printStackTrace();
            log.error("["+Constants.MSG_FIND_FAILED+"]:"+e.getMessage());
            return new ReturnEntityError(Constants.MSG_FIND_FAILED,null,null);
        }
    }

    /**
     * 查询详情
     *
     * @param id 主键
     * @return ReturnEntity
     */
    @GetMapping("/detail/{id}")
    public ReturnEntity selectById(@PathVariable("id") Long id){
        try{
            UserDepartment userDepartment = userDepartmentService.getById(id);
            if(null!= userDepartment){
                return new ReturnEntitySuccess(Constants.MSG_FIND_SUCCESS, userDepartment);
            }else{
                return new ReturnEntitySuccess(Constants.MSG_FIND_NOT_FOUND);
            }
        }catch(Exception e){
            e.printStackTrace();
            log.error("[id:{} " + Constants.MSG_FIND_FAILED + "]:{}", id, e.getMessage());
            return new ReturnEntityError(Constants.MSG_FIND_FAILED);
        }
    }

    /**
     * 新增
     *
     * @param userDepartment UserDepartment
     * @param result BindingResult
     * @return ReturnEntity
     */
    @PostMapping("/add")
    public ReturnEntity save(@Validated @RequestBody UserDepartment userDepartment,BindingResult result,
            HttpServletRequest request,HttpServletResponse response){
        if(result.hasErrors()){
            FieldError fieldError=result.getFieldErrors().get(0);
            String errorMsg=fieldError.getDefaultMessage();
            if(Constants.MSG_ERROR_CANNOT_NULL.equals(errorMsg)){
                errorMsg=fieldError.getField()+fieldError.getDefaultMessage();
            }
            return new ReturnEntityError(errorMsg,null, userDepartment);
        }else{
            try {
                QueryWrapper<UserDepartment> wrapper=new QueryWrapper<>();
                if(userDepartmentService.count(wrapper)>0){
                    return new ReturnEntityError(Constants.MSG_FIND_EXISTED, userDepartment);
                }else{
                    if(userDepartmentService.save(userDepartment)){
                        return new ReturnEntitySuccess(Constants.MSG_INSERT_SUCCESS, userDepartment);
                    }else{
                        return new ReturnEntityError(Constants.MSG_INSERT_FAILED, userDepartment);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("[" + Constants.MSG_INSERT_FAILED + "]:{}", e.getMessage());
                return new ReturnEntityError(Constants.MSG_INSERT_FAILED, userDepartment);
            }
        }
     }

    /**
     * 修改
     *
     * @param id 主键
     * @param userDepartment UserDepartment
     * @param result BindingResult
     * @return ReturnEntity
     */
    @PutMapping("/edit/{id}")
    public ReturnEntity updateById(@PathVariable("id") Long id,@Validated @RequestBody UserDepartment userDepartment,BindingResult result,
            HttpServletRequest request,HttpServletResponse response){
        if(result.hasErrors()){
            return new ReturnEntityError(result.getFieldErrors().get(0).getDefaultMessage(), userDepartment);
        }else{
            try {
                if(null== userDepartmentService.getById(id)){
                    return new ReturnEntityError(Constants.MSG_FIND_NOT_FOUND, userDepartment);
                }else{
                    if(userDepartmentService.updateById(userDepartment)){
                        return new ReturnEntitySuccess(Constants.MSG_UPDATE_SUCCESS, userDepartment);
                    }else{
                        return new ReturnEntityError(Constants.MSG_UPDATE_FAILED, userDepartment);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("[id:{} " + Constants.MSG_UPDATE_FAILED + "]:{}", id, e.getMessage());
                return new ReturnEntityError(Constants.MSG_UPDATE_FAILED, userDepartment);
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
    public ReturnEntity deleteById(@PathVariable("id") Long id){
        try {
            if(null== userDepartmentService.getById(id)){
                return new ReturnEntityError(Constants.MSG_FIND_NOT_FOUND,id);
            }else{
                if(userDepartmentService.removeById(id)){
                    return new ReturnEntitySuccess(Constants.MSG_DELETE_SUCCESS,id);
                }else{
                    return new ReturnEntityError(Constants.MSG_DELETE_FAILED,id);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[id:{} " + Constants.MSG_DELETE_FAILED + "]:{}", id, e.getMessage());
            return new ReturnEntityError(Constants.MSG_DELETE_FAILED, id);
        }
    }

}
