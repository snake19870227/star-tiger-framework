drop table if exists sys_org;

create table sys_org
(
    org_flow      varchar(32) not null comment '机构流水号',
    org_code      varchar(50) comment '机构代码',
    org_name      varchar(500) comment '机构名称',
    record_status varchar(2) default 'Y' comment '记录状态',
    primary key (org_flow)
);