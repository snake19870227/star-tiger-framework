drop table if exists sys_user_role;

create table sys_user_role
(
    user_role_flow varchar(32) not null comment '用户角色信息流水号',
    user_flow      varchar(32) comment '用户流水号',
    role_flow      varchar(32) comment '角色流水号',
    record_status  varchar(2) default 'Y' comment '记录状态',
    primary key (user_role_flow)
);