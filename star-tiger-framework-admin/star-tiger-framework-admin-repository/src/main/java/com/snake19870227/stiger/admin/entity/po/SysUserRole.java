package com.snake19870227.stiger.admin.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author buhuayang
 * @since 2020-08-30
 */
@ApiModel(value="SysUserRole对象", description="")
public class SysUserRole implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "用户角色信息流水号")
    @TableId(value = "user_role_flow", type = IdType.ASSIGN_UUID)
    private String userRoleFlow;

    @ApiModelProperty(value = "用户流水号")
    private String userFlow;

    @ApiModelProperty(value = "角色流水号")
    private String roleFlow;

    @ApiModelProperty(value = "记录状态")
    @TableLogic
    private String recordStatus;


    public String getUserRoleFlow() {
        return userRoleFlow;
    }

    public SysUserRole setUserRoleFlow(String userRoleFlow) {
        this.userRoleFlow = userRoleFlow;
        return this;
    }

    public String getUserFlow() {
        return userFlow;
    }

    public SysUserRole setUserFlow(String userFlow) {
        this.userFlow = userFlow;
        return this;
    }

    public String getRoleFlow() {
        return roleFlow;
    }

    public SysUserRole setRoleFlow(String roleFlow) {
        this.roleFlow = roleFlow;
        return this;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public SysUserRole setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
        return this;
    }

    @Override
    public String toString() {
        return "SysUserRole{" +
        "userRoleFlow=" + userRoleFlow +
        ", userFlow=" + userFlow +
        ", roleFlow=" + roleFlow +
        ", recordStatus=" + recordStatus +
        "}";
    }
}
