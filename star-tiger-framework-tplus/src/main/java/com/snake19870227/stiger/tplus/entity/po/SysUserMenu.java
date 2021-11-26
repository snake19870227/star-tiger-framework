package com.snake19870227.stiger.tplus.entity.po;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 
 * </p>
 *
 * @author buhuayang
 * @since 2021-08-25
 */
@TableName("SYS_USER_MENU")
@ApiModel(value="SysUserMenu对象", description="")
public class SysUserMenu implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "FLOW", type = IdType.ASSIGN_UUID)
    private String flow;

    @TableField("USER_ID")
    private String userId;

    @TableField("MENU_FLOW")
    private String menuFlow;


    public String getFlow() {
        return flow;
    }

    public SysUserMenu setFlow(String flow) {
        this.flow = flow;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public SysUserMenu setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getMenuFlow() {
        return menuFlow;
    }

    public SysUserMenu setMenuFlow(String menuFlow) {
        this.menuFlow = menuFlow;
        return this;
    }

    @Override
    public String toString() {
        return "SysUserMenu{" +
        "flow=" + flow +
        ", userId=" + userId +
        ", menuFlow=" + menuFlow +
        "}";
    }
}
