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
import java.util.List;

/**
 * <p>
 * 部门
 * </p>
 *
 * @author wj
 * @since 2020-06-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_department")
@ApiModel(value = "Department对象", description = "")
public class Department extends BaseEntity {

    private static final long serialVersionUID = -5002066309774320408L;

    @ApiModelProperty(value = "主键，部门编号")
    @TableId("id")
    private Long id;

    @ApiModelProperty(value = "部门编码")
    @TableField("dep_code")
    @NotNull(message = "部门编码不能为空")
    @Length(max = 64, message = "部门编码长度不能超过64个字符")
    private String depCode;

    @ApiModelProperty(value = "部门名称")
    @TableField("dep_name")
    @NotNull(message = "部门名称不能为空")
    @Length(max = 64, message = "部门名称长度不能超过64个字符")
    private String depName;

    @ApiModelProperty(value = "描述")
    @TableField("description")
    private String description;

    @ApiModelProperty(value = "上级部门ID")
    @TableField("parent_id")
    private Long parentId;

    @ApiModelProperty(value = "上级部门名称")
    @TableField(exist = false)
    private String parentName;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者ID")
    @TableField("create_user_id")
    private Long createUserId;

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

    @ApiModelProperty(value = "子部门列表")
    @TableField(exist = false)
    private List<Department> children;

    @TableField(exist = false)
    private boolean lay_is_open = false;
}
