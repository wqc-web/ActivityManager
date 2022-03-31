package com.zhongzhou.common.dto;

import com.zhongzhou.common.base.BaseBean;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @ClassName PasswordDTO
 * @Description 修改密码DTO
 * @Date 2020/6/28 13:59
 * @Author wj
 */
@Data
public class PasswordDTO extends BaseBean {
    /**
     * 初始化serialVersionUID
     */
    private static final long serialVersionUID = -4516299728929097517L;

    @NotNull(message = "旧密码不能为空")
    private String oldPassword;

    @NotNull(message = "新密码不能为空")
    @Length(min = 6, max = 12, message = "密码必须6到12位")
    private String newPassword;
}
