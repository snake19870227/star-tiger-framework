drop table sys_cfg;

create table sys_cfg
(
    cfg_code         varchar(50) constraint sys_cfg_pk primary key,
    cfg_value        varchar(4000),
    org_flow         varchar(32),
    record_status    varchar(2),
    last_modify_time varchar(20)
);

comment on table sys_cfg is '系统配置表';
comment on column sys_cfg.cfg_code is '配置代码';
comment on column sys_cfg.cfg_value is '配置内容';
comment on column sys_cfg.org_flow is '机构流水号';
comment on column sys_cfg.record_status is '记录状态';
comment on column sys_cfg.last_modify_time is '最后更新时间';