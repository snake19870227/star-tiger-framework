drop table if exists sys_module;

create table sys_module
(
    module_flow   varchar(32) not null comment '模块流水号',
    module_name   varchar(50) not null comment '模块名称',
    module_order  int comment '排序码',
    enable_flag   varchar(2) default 'Y' comment '启用标记',
    record_status varchar(2) default 'Y' comment '记录状态',
    primary key (module_flow)
);