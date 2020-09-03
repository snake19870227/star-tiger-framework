drop table if exists sys_role;

create table sys_role
(
    role_flow     varchar(32) not null comment '角色流水号',
    role_code     varchar(50) comment '角色代码',
    role_name     varchar(50) comment '角色名称',
    record_status varchar(2) default 'Y' comment '记录状态',
    primary key (role_flow)
);