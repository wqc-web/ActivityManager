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
 * 操作日志
 * </p>
 *
 * @author wj
 * @since 2020-06-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_operation_log")
@ApiModel(value = "OperationLog对象", description = "")
public class OperationLog extends BaseEntity {

    private static final long serialVersionUID = 5133724441991511561L;

    @ApiModelProperty(value = "主键")
    @TableId("id")
    private Long id;

    @ApiModelProperty(value = "操作名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "url")
    @TableField("url")
    private String url;

    @ApiModelProperty(value = "参数")
    @TableField("parameters")
    private String parameters;

    @ApiModelProperty(value = "操作人IP地址")
    @TableField("ip_addr")
    private String ipAddr;

    @ApiModelProperty(value = "操作人名称")
    @TableField("operation_user_name")
    private String operationUserName;

    @ApiModelProperty(value = "操作人ID")
    @TableField("operation_user_id")
    private Long operationUserId;

    @ApiModelProperty(value = "操作时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("operation_time")
    private LocalDateTime operationTime;

    @ApiModelProperty(value = "操作时间字符串")
    @TableField(exist = false)
    private String operationTimeStr;

    @ApiModelProperty(value = "版本，分布式事务标志")
    @TableField("version")
    @Version
    private Long version;


}
