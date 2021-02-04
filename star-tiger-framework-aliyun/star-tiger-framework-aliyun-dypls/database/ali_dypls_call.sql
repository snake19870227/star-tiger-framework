drop table if exists ali_dypls_call;

create table ali_dypls_call
(
    id                int auto_increment primary key comment '主键',
    sub_id            varchar(50) comment '绑定关系id',
    sub_type          varchar(20) comment '绑定类型',
    call_id           varchar(100) comment '通话记录id',
    phonea            varchar(30) comment 'A号码' not null,
    phoneb            varchar(30) comment 'B号码',
    phonex            varchar(30) comment 'X号码' not null,
    phonex_ext        varchar(30) comment 'X号码分机号',
    pool_key          varchar(50) comment '号码池' not null,
    is_recording      int default 0 comment '是否录音(0:否;1:是)',
    call_display_type int default 1 comment '号码显示逻辑(见阿里云文档)',
    call_type         int comment '呼叫类型(见阿里云文档)',
    call_time         varchar(50) comment '主叫拨打时间',
    start_time        varchar(50) comment '被叫接听时间',
    call_out_time     varchar(50) comment '呼叫由X送给B端局的时间',
    ring_time         varchar(50) comment '呼叫送被叫端局时，被叫端局响应的时间',
    free_ring_time    varchar(50) comment '被叫手机真实的振铃时间',
    release_time      varchar(50) comment '被叫挂断时间',
    release_dir       int comment '通话释放方向',
    unconnected_cause int comment '未接通通话的原因归类',
    release_cause     int comment '释放原因',
    out_id            varchar(100) comment '扩展',
    record_time       varchar(50) comment '录制成功时间',
    record_file_url   varchar(500) comment '录制文件路径',
    info              varchar(2000) comment '通话完成报告请求体'
);

create index ali_dypls_call_1_idx on ali_dypls_call (phonea);
create index ali_dypls_call_2_idx on ali_dypls_call (phoneb);
create index ali_dypls_call_3_idx on ali_dypls_call (phonex);
create index ali_dypls_call_4_idx on ali_dypls_call (sub_id);
create index ali_dypls_call_5_idx on ali_dypls_call (call_id);