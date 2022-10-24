package com.xunc.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;

/**
 * @author zfc generator
 * @since 2022-10-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "sys_log")
@ApiModel(value="SysLog", description="实体")
public class SysLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @TableField("id")
    private Integer id;

    @ApiModelProperty(value = "用户名")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty(value = "用户操作")
    @TableField("operation")
    private String operation;

    @ApiModelProperty(value = "响应时间")
    @TableField("time")
    private Integer time;

    @ApiModelProperty(value = "请求方法")
    @TableField("method")
    private String method;

    @ApiModelProperty(value = "请求参数")
    @TableField("params")
    private String params;

    @ApiModelProperty(value = "IP地址")
    @TableField("ip")
    private String ip;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Date createTime;
}