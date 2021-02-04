package com.snake19870227.stiger.aliyun.dypls.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author buhuayang
 * @since 2021-02-04
 */
@ApiModel(value="AliDyplsCall对象", description="")
public class AliDyplsCall implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "绑定关系id")
    private String subId;

    @ApiModelProperty(value = "绑定类型")
    private String subType;

    @ApiModelProperty(value = "通话记录id")
    private String callId;

    @ApiModelProperty(value = "A号码")
    private String phonea;

    @ApiModelProperty(value = "B号码")
    private String phoneb;

    @ApiModelProperty(value = "X号码")
    private String phonex;

    @ApiModelProperty(value = "X号码分机号")
    private String phonexExt;

    @ApiModelProperty(value = "号码池")
    private String poolKey;

    @ApiModelProperty(value = "是否录音(0:否;1:是)")
    private Integer isRecording;

    @ApiModelProperty(value = "号码显示逻辑(见阿里云文档)")
    private Integer callDisplayType;

    @ApiModelProperty(value = "呼叫类型(见阿里云文档)")
    private Integer callType;

    @ApiModelProperty(value = "主叫拨打时间")
    private String callTime;

    @ApiModelProperty(value = "被叫接听时间")
    private String startTime;

    @ApiModelProperty(value = "呼叫由X送给B端局的时间")
    private String callOutTime;

    @ApiModelProperty(value = "呼叫送被叫端局时，被叫端局响应的时间")
    private String ringTime;

    @ApiModelProperty(value = "被叫手机真实的振铃时间")
    private String freeRingTime;

    @ApiModelProperty(value = "被叫挂断时间")
    private String releaseTime;

    @ApiModelProperty(value = "通话释放方向")
    private Integer releaseDir;

    @ApiModelProperty(value = "未接通通话的原因归类")
    private Integer unconnectedCause;

    @ApiModelProperty(value = "释放原因")
    private Integer releaseCause;

    @ApiModelProperty(value = "扩展")
    private String outId;

    @ApiModelProperty(value = "录制成功时间")
    private String recordTime;

    @ApiModelProperty(value = "录制文件路径")
    private String recordFileUrl;

    @ApiModelProperty(value = "通话完成报告请求体")
    private String info;


    public Integer getId() {
        return id;
    }

    public AliDyplsCall setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getSubId() {
        return subId;
    }

    public AliDyplsCall setSubId(String subId) {
        this.subId = subId;
        return this;
    }

    public String getSubType() {
        return subType;
    }

    public AliDyplsCall setSubType(String subType) {
        this.subType = subType;
        return this;
    }

    public String getCallId() {
        return callId;
    }

    public AliDyplsCall setCallId(String callId) {
        this.callId = callId;
        return this;
    }

    public String getPhonea() {
        return phonea;
    }

    public AliDyplsCall setPhonea(String phonea) {
        this.phonea = phonea;
        return this;
    }

    public String getPhoneb() {
        return phoneb;
    }

    public AliDyplsCall setPhoneb(String phoneb) {
        this.phoneb = phoneb;
        return this;
    }

    public String getPhonex() {
        return phonex;
    }

    public AliDyplsCall setPhonex(String phonex) {
        this.phonex = phonex;
        return this;
    }

    public String getPhonexExt() {
        return phonexExt;
    }

    public AliDyplsCall setPhonexExt(String phonexExt) {
        this.phonexExt = phonexExt;
        return this;
    }

    public String getPoolKey() {
        return poolKey;
    }

    public AliDyplsCall setPoolKey(String poolKey) {
        this.poolKey = poolKey;
        return this;
    }

    public Integer getIsRecording() {
        return isRecording;
    }

    public AliDyplsCall setIsRecording(Integer isRecording) {
        this.isRecording = isRecording;
        return this;
    }

    public Integer getCallDisplayType() {
        return callDisplayType;
    }

    public AliDyplsCall setCallDisplayType(Integer callDisplayType) {
        this.callDisplayType = callDisplayType;
        return this;
    }

    public Integer getCallType() {
        return callType;
    }

    public AliDyplsCall setCallType(Integer callType) {
        this.callType = callType;
        return this;
    }

    public String getCallTime() {
        return callTime;
    }

    public AliDyplsCall setCallTime(String callTime) {
        this.callTime = callTime;
        return this;
    }

    public String getStartTime() {
        return startTime;
    }

    public AliDyplsCall setStartTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    public String getCallOutTime() {
        return callOutTime;
    }

    public AliDyplsCall setCallOutTime(String callOutTime) {
        this.callOutTime = callOutTime;
        return this;
    }

    public String getRingTime() {
        return ringTime;
    }

    public AliDyplsCall setRingTime(String ringTime) {
        this.ringTime = ringTime;
        return this;
    }

    public String getFreeRingTime() {
        return freeRingTime;
    }

    public AliDyplsCall setFreeRingTime(String freeRingTime) {
        this.freeRingTime = freeRingTime;
        return this;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public AliDyplsCall setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
        return this;
    }

    public Integer getReleaseDir() {
        return releaseDir;
    }

    public AliDyplsCall setReleaseDir(Integer releaseDir) {
        this.releaseDir = releaseDir;
        return this;
    }

    public Integer getUnconnectedCause() {
        return unconnectedCause;
    }

    public AliDyplsCall setUnconnectedCause(Integer unconnectedCause) {
        this.unconnectedCause = unconnectedCause;
        return this;
    }

    public Integer getReleaseCause() {
        return releaseCause;
    }

    public AliDyplsCall setReleaseCause(Integer releaseCause) {
        this.releaseCause = releaseCause;
        return this;
    }

    public String getOutId() {
        return outId;
    }

    public AliDyplsCall setOutId(String outId) {
        this.outId = outId;
        return this;
    }

    public String getRecordTime() {
        return recordTime;
    }

    public AliDyplsCall setRecordTime(String recordTime) {
        this.recordTime = recordTime;
        return this;
    }

    public String getRecordFileUrl() {
        return recordFileUrl;
    }

    public AliDyplsCall setRecordFileUrl(String recordFileUrl) {
        this.recordFileUrl = recordFileUrl;
        return this;
    }

    public String getInfo() {
        return info;
    }

    public AliDyplsCall setInfo(String info) {
        this.info = info;
        return this;
    }

    @Override
    public String toString() {
        return "AliDyplsCall{" +
        "id=" + id +
        ", subId=" + subId +
        ", subType=" + subType +
        ", callId=" + callId +
        ", phonea=" + phonea +
        ", phoneb=" + phoneb +
        ", phonex=" + phonex +
        ", phonexExt=" + phonexExt +
        ", poolKey=" + poolKey +
        ", isRecording=" + isRecording +
        ", callDisplayType=" + callDisplayType +
        ", callType=" + callType +
        ", callTime=" + callTime +
        ", startTime=" + startTime +
        ", callOutTime=" + callOutTime +
        ", ringTime=" + ringTime +
        ", freeRingTime=" + freeRingTime +
        ", releaseTime=" + releaseTime +
        ", releaseDir=" + releaseDir +
        ", unconnectedCause=" + unconnectedCause +
        ", releaseCause=" + releaseCause +
        ", outId=" + outId +
        ", recordTime=" + recordTime +
        ", recordFileUrl=" + recordFileUrl +
        ", info=" + info +
        "}";
    }
}
