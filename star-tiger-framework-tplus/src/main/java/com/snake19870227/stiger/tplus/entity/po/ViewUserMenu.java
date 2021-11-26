package com.snake19870227.stiger.tplus.entity.po;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 
 * </p>
 *
 * @author buhuayang
 * @since 2021-08-25
 */
@TableName("VIEW_USER_MENU")
@ApiModel(value="ViewUserMenu对象", description="")
public class ViewUserMenu implements Serializable {

    private static final long serialVersionUID=1L;

    @TableField("MENU_FLOW")
    private String menuFlow;

    @TableField("MENU_NAME")
    private String menuName;

    @TableField("MENU_PATH")
    private String menuPath;

    @TableField("USER_ID")
    private String userId;


    public String getMenuFlow() {
        return menuFlow;
    }

    public ViewUserMenu setMenuFlow(String menuFlow) {
        this.menuFlow = menuFlow;
        return this;
    }

    public String getMenuName() {
        return menuName;
    }

    public ViewUserMenu setMenuName(String menuName) {
        this.menuName = menuName;
        return this;
    }

    public String getMenuPath() {
        return menuPath;
    }

    public ViewUserMenu setMenuPath(String menuPath) {
        this.menuPath = menuPath;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public ViewUserMenu setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    @Override
    public String toString() {
        return "ViewUserMenu{" +
        "menuFlow=" + menuFlow +
        ", menuName=" + menuName +
        ", menuPath=" + menuPath +
        ", userId=" + userId +
        "}";
    }
}
