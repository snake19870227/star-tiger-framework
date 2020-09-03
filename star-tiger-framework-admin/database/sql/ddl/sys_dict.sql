drop table if exists sys_dict;

create table sys_dict
(
    dict_flow     varchar(50) not null comment '字典项目流水号',
    dict_code     varchar(50) not null comment '字典项目编码',
    dict_name     varchar(50) not null comment '字典项目名称',
    record_status varchar(2) default 'Y' comment '记录状态',
    primary key (dict_flow)
);