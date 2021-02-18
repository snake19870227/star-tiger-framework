drop table if exists pay_refund;

create table pay_refund
(
    refund_flow        varchar(50)    not null comment '退款申请流水号',
    trade_flow         varchar(50)    not null comment '订单流水号',
    biz_type           varchar(50)             comment '业务类型',
    biz_flow           varchar(50)             comment '业务记录流水号',
    status_id          varchar(50)    not null comment '退款状态ID',
    status_name        varchar(50)             comment '退款状态名称',
    pay_channel_id     varchar(50)    not null comment '支付渠道类型ID',
    pay_channel_name   varchar(50)    not null comment '支付渠道类型名称',
    pay_app_id         varchar(50)    not null comment '支付渠道应用标识',
    out_trade_no       varchar(100)   not null comment '本地商户订单号',
    payment_method     varchar(50)             comment '订单支付类型',
    out_refund_no      varchar(50)             comment '本地商户退款单号',
    refund_no          varchar(50)             comment '支付渠道退款单号',
    trade_price        numeric(10, 2) not null comment '订单金额(单位:元)',
    refund_price       numeric(10, 2) not null comment '退款金额(单位:元)',
    refund_create_time varchar(20)    not null comment '退款申请创建时间',
    record_status      varchar(2)  default 'Y' comment '记录状态',
    primary key (refund_flow)
) comment '退款申请';