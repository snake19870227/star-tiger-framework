package com.snake19870227.stiger.tplus.entity.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * T+操作token
 * </p>
 *
 * @author buhuayang
 * @since 2021-08-25
 */
@TableName("TP_TOKEN")
@ApiModel(value="TpToken对象", description="T+操作token")
public class TpToken implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "FLOW", type = IdType.ASSIGN_UUID)
    private String flow;

    @ApiModelProperty(value = "获取时间")
    @TableField("DATETIME")
    private String datetime;

    @ApiModelProperty(value = "access_token")
    @TableField("ACCESS_TOKEN")
    private String accessToken;

    @ApiModelProperty(value = "expires_in")
    @TableField("EXPIRES_IN")
    private Long expiresIn;

    @TableField("EXPIRES_DATETIME")
    private String expiresDatetime;

    @ApiModelProperty(value = "refresh_token")
    @TableField("REFRESH_TOKEN")
    private String refreshToken;

    @ApiModelProperty(value = "refresh_expires_in")
    @TableField("REFRESH_EXPIRES_IN")
    private Long refreshExpiresIn;

    @TableField("REFRESH_EXPIRES_DATETIME")
    private String refreshExpiresDatetime;

    @ApiModelProperty(value = "scope")
    @TableField("SCOPE")
    private String scope;

    @ApiModelProperty(value = "user_id")
    @TableField("USER_ID")
    private String userId;

    @ApiModelProperty(value = "org_id")
    @TableField("ORG_ID")
    private String orgId;

    @ApiModelProperty(value = "app_name")
    @TableField("APP_NAME")
    private String appName;

    @ApiModelProperty(value = "sid")
    @TableField("SID")
    private String sid;

    @ApiModelProperty(value = "user_auth_permanent_code")
    @TableField("USER_AUTH_PERMANENT_CODE")
    private String userAuthPermanentCode;

    @ApiModelProperty(value = "用户姓名")
    @TableField("USER_NAME")
    private String userName;


    public String getFlow() {
        return flow;
    }

    public TpToken setFlow(String flow) {
        this.flow = flow;
        return this;
    }

    public String getDatetime() {
        return datetime;
    }

    public TpToken setDatetime(String datetime) {
        this.datetime = datetime;
        return this;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public TpToken setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public TpToken setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
        return this;
    }

    public String getExpiresDatetime() {
        return expiresDatetime;
    }

    public TpToken setExpiresDatetime(String expiresDatetime) {
        this.expiresDatetime = expiresDatetime;
        return this;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public TpToken setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }

    public Long getRefreshExpiresIn() {
        return refreshExpiresIn;
    }

    public TpToken setRefreshExpiresIn(Long refreshExpiresIn) {
        this.refreshExpiresIn = refreshExpiresIn;
        return this;
    }

    public String getRefreshExpiresDatetime() {
        return refreshExpiresDatetime;
    }

    public TpToken setRefreshExpiresDatetime(String refreshExpiresDatetime) {
        this.refreshExpiresDatetime = refreshExpiresDatetime;
        return this;
    }

    public String getScope() {
        return scope;
    }

    public TpToken setScope(String scope) {
        this.scope = scope;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public TpToken setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getOrgId() {
        return orgId;
    }

    public TpToken setOrgId(String orgId) {
        this.orgId = orgId;
        return this;
    }

    public String getAppName() {
        return appName;
    }

    public TpToken setAppName(String appName) {
        this.appName = appName;
        return this;
    }

    public String getSid() {
        return sid;
    }

    public TpToken setSid(String sid) {
        this.sid = sid;
        return this;
    }

    public String getUserAuthPermanentCode() {
        return userAuthPermanentCode;
    }

    public TpToken setUserAuthPermanentCode(String userAuthPermanentCode) {
        this.userAuthPermanentCode = userAuthPermanentCode;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public TpToken setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    @Override
    public String toString() {
        return "TpToken{" +
        "flow=" + flow +
        ", datetime=" + datetime +
        ", accessToken=" + accessToken +
        ", expiresIn=" + expiresIn +
        ", expiresDatetime=" + expiresDatetime +
        ", refreshToken=" + refreshToken +
        ", refreshExpiresIn=" + refreshExpiresIn +
        ", refreshExpiresDatetime=" + refreshExpiresDatetime +
        ", scope=" + scope +
        ", userId=" + userId +
        ", orgId=" + orgId +
        ", appName=" + appName +
        ", sid=" + sid +
        ", userAuthPermanentCode=" + userAuthPermanentCode +
        ", userName=" + userName +
        "}";
    }
}
