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
 * 角色和权限菜单关联关系
 * </p>
 *
 * @author wj
 * @since 2020-07-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_role_permission")
@ApiModel(value = "RolePermission对象", description = "")
public class RolePermission extends BaseEntity {

    private static final long serialVersionUID = -7201900748953259074L;

    @TableId("id")
    private Long id;

    @ApiModelProperty(value = "角色id")
    @TableField("role_id")
    private Long roleId;

    @ApiModelProperty(value = "权限菜单id")
    @TableField("per_id")
    private Long perId;

    @ApiModelProperty(value = "版本，分布式事务标志")
    @TableField("version")
    @Version
    private Long version;


}
