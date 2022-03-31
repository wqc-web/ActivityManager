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
 * 权限菜单
 * </p>
 *
 * @author wj
 * @since 2020-06-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_permission")
@ApiModel(value = "Permission对象", description = "")
public class Permission extends BaseEntity {

    private static final long serialVersionUID = 32130641412138742L;

    @ApiModelProperty(value = "权限菜单ID")
    @TableId("id")
    private Long id;

    @ApiModelProperty(value = "权限菜单名称")
    @TableField("name")
    @NotNull(message = "权限菜单名称不能为空")
    @Length(max = 64, message = "权限菜单名称长度不能超过64个字符")
    private String name;

    @ApiModelProperty(value = "权限菜单Code")
    @TableField("code")
    @NotNull(message = "权限菜单Code不能为空")
    @Length(max = 64, message = "权限菜单Code长度不能超过64个字符")
    private String code;

    @ApiModelProperty(value = "父级ID")
    @TableField("parent_id")
    private Long parentId;

    @ApiModelProperty(value = "父级名称")
    @TableField(exist = false)
    private String parentName;

    @ApiModelProperty(value = "类型，0：根菜单，1：子菜单，2：接口")
    @TableField("type")
    private Integer type;

    @ApiModelProperty(value = "链接地址")
    @TableField("url")
    private String url;

    @ApiModelProperty(value = "对应页面地址")
    @TableField("jump")
    private String jump;

    @ApiModelProperty(value = "级别")
    @TableField("level")
    private Integer level;

    @ApiModelProperty(value = "图标")
    @TableField("icon")
    private String icon;

    @ApiModelProperty(value = "图标附件地址")
    @TableField(exist = false)
    private Attachment iconAttachment;

    @ApiModelProperty(value = "排序")
    @TableField("seq")
    private Integer seq;

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

    @ApiModelProperty(value = "子菜单列表")
    @TableField(exist = false)
    private List<Permission> children;

    @ApiModelProperty(value = "图标附件ID集合")
    @TableField(exist = false)
    private List<Long> iconImgs;

    @ApiModelProperty(value = "图标附件集合")
    @TableField(exist = false)
    private List<Attachment> iconAttachments;
}
