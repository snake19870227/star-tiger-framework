drop table if exists sms_template;

create table sms_template
(
    id               varchar(50) not null primary key comment '主键',
    channel          varchar(50) not null comment '发送渠道',
    template_code    varchar(50) comment '短信模板code',
    commit_datetime  varchar(20) comment '短信模板创建时间',
    template_content varchar(2000) comment '短信模板内容',
    template_name    varchar(50) comment '短信模板name',
    template_status  varchar(20) comment '模板状态',
    template_type    varchar(20) comment '模板类型',
    remark           varchar(500) comment '备注'
) comment '短信模板表';