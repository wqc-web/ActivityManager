package com.zhongzhou.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhongzhou.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * <p>
 * 系统用户
 * </p>
 *
 * @author wj
 * @since 2020-06-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_sys_user")
@ApiModel(value = "SysUser对象", description = "")
public class SysUser extends BaseEntity {

    private static final long serialVersionUID = 2644030401588727030L;

    @ApiModelProperty(value = "用户ID")
    @TableId("id")
    private Long id;

    @ApiModelProperty(value = "头像地址")
    @TableField("head_img")
    private String headImg;

    @ApiModelProperty(value = "微信ID")
    @TableField("open_id")
    private String openId;

    @ApiModelProperty(value = "用户名")
    @TableField("user_name")
    @NotNull(message = "用户名不能为空")
    @Length(max = 64, message = "用户名长度不能超过64个字符")
    private String userName;

    @ApiModelProperty(value = "密码")
    @TableField("user_password")
    private String userPassword;

    @ApiModelProperty(value = "用户编码")
    @TableField("user_code")
    private String userCode;

    @ApiModelProperty(value = "真实姓名")
    @TableField("real_name")
    @NotNull(message = "真实姓名不能为空")
    @Length(max = 64, message = "真实姓名长度不能超过64个字符")
    private String realName;

    @ApiModelProperty(value = "身份证号码")
    @TableField("identity_no")
    @Length(max = 18, message = "身份证号码长度不能超过18个字符")
    private String identityNo;

    @ApiModelProperty(value = "邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty(value = "电话号码")
    @TableField("phone")
    @Length(max = 13, message = "电话号码长度不能超过13个字符")
    private String phone;

    @ApiModelProperty(value = "性别，0：男，1：女")
    @TableField("sex")
    private Integer sex;

    @ApiModelProperty(value = "级别")
    @TableField("level")
    private Integer level;

    @ApiModelProperty(value = "级别名称")
    @TableField("level_name")
    private String levelName;

    @ApiModelProperty(value = "创建者ID")
    @TableField("create_user_id")
    private Long createUserId;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "最后一次修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("last_update_time")
    private LocalDateTime lastUpdateTime;

    @ApiModelProperty(value = "最后一次修改者ID")
    @TableField("last_update_user_id")
    private Long lastUpdateUserId;

    @ApiModelProperty(value = "删除标志，0：未删除，1：已删除")
    @TableField("delete_flag")
    @TableLogic
    private Integer deleteFlag;

    @ApiModelProperty(value = "版本，分布式事务标志")
    @TableField("version")
    @Version
    private Long version;

    @ApiModelProperty(value = "状态，1：启用；0：冻结")
    @TableField("state")
    private Integer state;

    @ApiModelProperty(value = "盐")
    @TableField("salt")
    private String salt;

    @ApiModelProperty(value = "角色ID")
    @TableField(exist = false)
    private Long roleId;

    @ApiModelProperty(value = "角色名称")
    @TableField(exist = false)
    private String roleName;

    @ApiModelProperty(value = "部门ID")
    @TableField(exist = false)
    private Long depId;

    @ApiModelProperty(value = "部门名称")
    @TableField(exist = false)
    private String depName;

    @TableField(exist = false)
    private Long activityId;

}
