drop table tp_message;

create table tp_message
(
    flow        varchar(50) constraint tp_message_pk primary key,
    msg_id      varchar(99),
    app_key     varchar(99),
    msg_type    varchar(99),
    msg_time    varchar(20),
    biz_content varchar(2000),
    create_time varchar(20),
    modify_time varchar(20)
);

comment on table tp_message is 'T+消息推送日志';
comment on column tp_message.flow is '主键';
comment on column tp_message.msg_id is '消息ID';
comment on column tp_message.app_key is '开放平台appKey';
comment on column tp_message.msg_type is '消息类型';
comment on column tp_message.msg_time is '时间戳';
comment on column tp_message.biz_content is '消息体';