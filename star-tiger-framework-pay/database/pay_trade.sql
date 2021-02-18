drop table if exists pay_trade;

create table pay_trade
(
    trade_flow          varchar(50)    not null comment '订单流水号',
    biz_type            varchar(50)    not null comment '业务类型',
    biz_flow            varchar(50)             comment '业务记录流水号',
    status_id           varchar(50)    not null comment '订单状态ID',
    status_name         varchar(50)             comment '订单状态名称',
    pay_channel_id      varchar(50)    not null comment '支付渠道类型ID',
    pay_channel_name    varchar(50)    not null comment '支付渠道类型名称',
    pay_app_id          varchar(50)    not null comment '支付渠道应用标识',
    out_trade_no        varchar(100)   not null comment '本地商户订单号',
    payment_method      varchar(50)             comment '订单支付类型',
    trade_no            varchar(100)            comment '支付渠道订单号',
    trade_buyer_account varchar(100)            comment '购买用户支付渠道账户',
    trade_buyer_id      varchar(100)            comment '购买用户支付渠道内ID',
    trade_price         numeric(10, 2) not null comment '订单金额(单位:元)',
    trade_create_time   varchar(20)    not null comment '订单创建时间',
    trade_expire_time   varchar(20)    not null comment '订单失效时间',
    trade_success_time  varchar(20)             comment '订单支付成功时间',
    trade_status        varchar(50)             comment '渠道订单状态',
    trade_status_des    varchar(500)            comment '渠道订单状态描述',
    is_refund           varchar(2)  default 'N' comment '是退款',
    req_content         varchar(4000)           comment '支付渠道创建订单请求内容',
    resp_content        varchar(4000)           comment '支付渠道创建订单响应内容',
    record_status       varchar(2)  default 'Y' comment '记录状态',
    primary key (trade_flow)
) comment '支付订单';