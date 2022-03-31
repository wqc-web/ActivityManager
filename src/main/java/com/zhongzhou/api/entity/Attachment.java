package com.zhongzhou.api.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhongzhou.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * <p>
 * 附件
 * </p>
 *
 * @author wj
 * @since 2020-06-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_attachment")
@ApiModel(value = "Attachment对象", description = "")
public class Attachment extends BaseEntity {

    private static final long serialVersionUID = 3203592528082443972L;

    @TableId("id")
    private Long id;

    @ApiModelProperty(value = "附件名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "地址url")
    @TableField("url")
    private String url;

    @ApiModelProperty(value = "简述")
    @TableField("des")
    private String des;

    @ApiModelProperty(value = "附件类型")
    @TableField("type")
    private Long type;

    @ApiModelProperty(value = "附件类型名称")
    @TableField("type_name")
    private String typeName;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者ID")
    @TableField("create_user_id")
    private Long createUserId;

    @ApiModelProperty(value = "来源ID")
    @TableField("source_id")
    private Long sourceId;

    @TableField("version")
    @Version
    private Long version;


}
