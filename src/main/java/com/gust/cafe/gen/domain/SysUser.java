package com.gust.cafe.gen.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 请输入类描述
 */
@ApiModel(description = "sys_user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sys_user")
public class SysUser {
    @Id
    @Column(name = "id")
    @ApiModelProperty(value = "")
    private String id;

    @Column(name = "display_name")
    @ApiModelProperty(value = "")
    private String displayName;

    @Column(name = "avatar")
    @ApiModelProperty(value = "")
    private String avatar;

    @Column(name = "username")
    @ApiModelProperty(value = "")
    private String username;

    @Column(name = "\"password\"")
    @ApiModelProperty(value = "")
    private String password;

    @Column(name = "email")
    @ApiModelProperty(value = "")
    private String email;

    @Column(name = "is_enabled")
    @ApiModelProperty(value = "")
    private Integer isEnabled;

    @Column(name = "create_by")
    @ApiModelProperty(value = "")
    private String createBy;

    @Column(name = "create_time")
    @ApiModelProperty(value = "")
    private String createTime;

    @Column(name = "update_by")
    @ApiModelProperty(value = "")
    private String updateBy;

    @Column(name = "update_time")
    @ApiModelProperty(value = "")
    private String updateTime;
}