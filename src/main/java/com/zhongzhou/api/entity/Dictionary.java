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
 * 数据字典
 * </p>
 *
 * @author wj
 * @since 2020-06-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_dictionary")
@ApiModel(value = "Dictionary对象", description = "")
public class Dictionary extends BaseEntity {

    private static final long serialVersionUID = -7678776780407738336L;

    @ApiModelProperty(value = "数据字典ID")
    @TableId("id")
    private Long id;

    @ApiModelProperty(value = "数据字典值")
    @TableField("dic_value")
    @NotNull(message = "数据字典值不能为空")
    @Length(max = 64, message = "数据字典值长度不能超过64个字符")
    private String dicValue;

    @ApiModelProperty(value = "数据字典名称")
    @TableField("dic_name")
    @NotNull(message = "数据字典名称不能为空")
    @Length(max = 64, message = "数据字典名称长度不能超过64个字符")
    private String dicName;

    @ApiModelProperty(value = "数据字典编码")
    @TableField("dic_code")
    @NotNull(message = "数据字典编码不能为空")
    @Length(max = 64, message = "数据字典编码长度不能超过64个字符")
    private String dicCode;

    @ApiModelProperty(value = "数据字典类型")
    @TableField("type")
    @NotNull(message = "数据字典类型不能为空")
    private Long type;

    @ApiModelProperty(value = "数据字典类型名称")
    @TableField("type_name")
    private String typeName;

    @ApiModelProperty(value = "数据字典类型编码")
    @TableField("type_code")
    private String typeCode;

    @ApiModelProperty(value = "描述")
    @TableField("description")
    private String description;

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


}
