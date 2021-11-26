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
@TableName("SYS_MENU")
@ApiModel(value="SysMenu对象", description="")
public class SysMenu implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "FLOW", type = IdType.ASSIGN_UUID)
    private String flow;

    @TableField("MENU_NAME")
    private String menuName;

    @TableField("MENU_PATH")
    private String menuPath;


    public String getFlow() {
        return flow;
    }

    public SysMenu setFlow(String flow) {
        this.flow = flow;
        return this;
    }

    public String getMenuName() {
        return menuName;
    }

    public SysMenu setMenuName(String menuName) {
        this.menuName = menuName;
        return this;
    }

    public String getMenuPath() {
        return menuPath;
    }

    public SysMenu setMenuPath(String menuPath) {
        this.menuPath = menuPath;
        return this;
    }

    @Override
    public String toString() {
        return "SysMenu{" +
        "flow=" + flow +
        ", menuName=" + menuName +
        ", menuPath=" + menuPath +
        "}";
    }
}
