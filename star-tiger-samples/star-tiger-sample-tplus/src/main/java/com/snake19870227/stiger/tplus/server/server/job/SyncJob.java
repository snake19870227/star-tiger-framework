package com.snake19870227.stiger.tplus.server.server.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.snake19870227.stiger.core.context.StarTigerContext;
import com.snake19870227.stiger.tplus.server.server.svc.ITplusSyncSvc;

@Component
public class SyncJob {

    private static final Logger logger = LoggerFactory.getLogger(SyncJob.class);

    private final ITplusSyncSvc tplusSyncSvc;

    public SyncJob(ITplusSyncSvc tplusSyncSvc) {
        this.tplusSyncSvc = tplusSyncSvc;
    }

    @Scheduled(cron = "0 0,30 * * * ?")
    public void doSync() {
        boolean isDev = StarTigerContext.isProfileActived("dev", "devwww");
        if (isDev) {
            logger.warn("开发环境不执行定时任务");
            return;
        }
        tplusSyncSvc.syncWarehouse();
        tplusSyncSvc.syncInventory();
        tplusSyncSvc.syncPartner();
        tplusSyncSvc.syncPerson();
    }
}
