drop table if exists ali_dypls_bind;

create table ali_dypls_bind
(
    id                varchar(50) comment '主键' primary key,
    sub_id            varchar(50) comment '绑定关系id',
    sub_type          varchar(20) comment '绑定类型',
    phonea            varchar(30) comment 'A号码' not null,
    phoneb            varchar(30) comment 'B号码',
    phonex            varchar(30) comment 'X号码' not null,
    phonex_ext        varchar(30) comment 'X号码分机号',
    pool_key          varchar(50) comment '号码池' not null,
    expire_time       varchar(20) comment '过期时间(yyyyMMddHHmmss)',
    is_recording      int default 0 comment '是否录音(0:否;1:是)',
    city              varchar(20) comment '绑定城市',
    call_display_type int default 1 comment '号码显示逻辑(见阿里云文档)',
    status            int default 0 comment '状态(0:未绑定;1:已绑定;2:已解绑)',
    out_id            varchar(100) comment '扩展'
);

create index ali_dypls_bind_1_idx on ali_dypls_bind (phonea);
create index ali_dypls_bind_2_idx on ali_dypls_bind (phoneb);
create index ali_dypls_bind_3_idx on ali_dypls_bind (phonex);
create index ali_dypls_bind_4_idx on ali_dypls_bind (status);