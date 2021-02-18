drop table if exists pay_notify;

create table pay_notify
(
    notify_flow      varchar(50)   not null comment '通知流水号',
    notify_time      varchar(20)   not null comment '通知时间',
    notify_type_id   varchar(50)            comment '通知类型',
    notify_type_name varchar(50)            comment '通知类型',
    pay_way          varchar(50)            comment '支付途径',
    pay_channel_id   varchar(50)   not null comment '支付渠道类型ID',
    pay_channel_name varchar(50)   not null comment '支付渠道类型名称',
    notify_id        varchar(50)            comment '通知标识',
    out_trade_no     varchar(100)           comment '本地商户订单号',
    trade_no         varchar(100)           comment '支付渠道订单号',
    notify_content   varchar(4000) not null comment '通知内容',
    record_status    varchar(2) default 'Y' comment '记录状态',
    primary key (notify_flow)
) comment '异步通知';