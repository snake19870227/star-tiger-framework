package com.snake19870227.stiger.aliyun.dypls.event;

import org.springframework.context.ApplicationEvent;
import com.snake19870227.stiger.aliyun.dypls.entity.po.AliDyplsCall;

/**
 * @author BuHuaYang
 * 2021/2/5
 */
public class SecretRecordingEvent extends ApplicationEvent implements SecretEvent {
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param info the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public SecretRecordingEvent(SecretRecordInfo info) {
        super(info);
    }

    public static class SecretRecordInfo {

        private AliDyplsCall call;

        private String downloadUrl;

        public SecretRecordInfo() {
        }

        public SecretRecordInfo(AliDyplsCall call, String downloadUrl) {
            this.call = call;
            this.downloadUrl = downloadUrl;
        }

        public AliDyplsCall getCall() {
            return call;
        }

        public void setCall(AliDyplsCall call) {
            this.call = call;
        }

        public String getDownloadUrl() {
            return downloadUrl;
        }

        public void setDownloadUrl(String downloadUrl) {
            this.downloadUrl = downloadUrl;
        }
    }
}
