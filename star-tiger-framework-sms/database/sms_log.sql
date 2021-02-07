drop table if exists sms_log;

create table sms_log
(
    id              int auto_increment primary key comment '主键',
    phone           varchar(16) not null comment '手机号',
    channel         varchar(50) not null comment '发送渠道',
    sign_name       varchar(50) comment '签名',
    template_id     varchar(50) comment '短信模板id',
    template_code   varchar(50) comment '短信模板code',
    template_name   varchar(50) comment '短信模板name',
    send_param      varchar(2000) comment '发送参数',
    status          int comment '状态(0:未发送;1:已发送;2:发送失败;3:已接收;4:接收失败)' default 0,
    create_datetime varchar(20) not null comment '创建时间',
    send_datetime   varchar(20) comment '发送时间(yyyyMMddHHmmss)',
    send_id         varchar(50) comment '短信平台发送id',
    send_code       varchar(50) comment '短信发送状态编码',
    send_msg        varchar(200) comment '短信发送状态说明',
    report_datetime varchar(20) comment '短信发送回执时间(yyyyMMddHHmmss)',
    report_success  int comment '是否接受成功(0:否;1:是)'                     default 0,
    report_code     varchar(50) comment '短信发送结果状态编码',
    report_msg      varchar(200) comment '短信发送结果状态说明',
    sms_size        int comment '短信个数',
    retry_count     int comment '重试次数'                                default 0
);

create index sms_log_1_index on sms_log (phone);
create index sms_log_2_index on sms_log (status);
create index sms_log_3_index on sms_log (channel);
create index sms_log_4_index on sms_log (send_id);