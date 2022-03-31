package com.zhongzhou.api.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhongzhou.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 角色
 * </p>
 *
 * @author wj
 * @since 2020-06-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_role")
@ApiModel(value="Role对象", description="")
public class Role extends BaseEntity {

    private static final long serialVersionUID = 4780498131220766739L;

    @ApiModelProperty(value = "角色Id")
    @TableId("id")
    private Long id;

    @ApiModelProperty(value = "角色名称")
    @TableField("role_name")
    @NotNull(message = "角色名称不能为空")
    @Length(max = 64, message = "角色名称长度不能超过64个字符")
    private String roleName;

    @ApiModelProperty(value = "简述")
    @TableField("description")
    private String description;

    @ApiModelProperty(value = "创建者ID")
    @TableField("create_user_id")
    private Long createUserId;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "最后一次修改者ID")
    @TableField("last_update_user_id")
    private Long lastUpdateUserId;

    @ApiModelProperty(value = "最后一次修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("last_update_time")
    private LocalDateTime lastUpdateTime;

    @ApiModelProperty(value = "删除标志，0：未删除，1：已删除")
    @TableField("delete_flag")
    @TableLogic
    private Integer deleteFlag;

    @ApiModelProperty(value = "版本，分布式事务标志")
    @TableField("version")
    @Version
    private Long version;


}
