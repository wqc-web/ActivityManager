package com.zhongzhou.api.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.zhongzhou.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户和部门关联表
 * </p>
 *
 * @author wj
 * @since 2020-06-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_user_department")
@ApiModel(value = "UserDepartment对象", description = "")
public class UserDepartment extends BaseEntity {

    private static final long serialVersionUID = -3457701040019187799L;

    @TableId("id")
    private Long id;

    @ApiModelProperty(value = "用户ID")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "部门ID")
    @TableField("dep_id")
    private Long depId;

    @ApiModelProperty(value = "版本，分布式事务标志")
    @TableField("version")
    @Version
    private Long version;


}
