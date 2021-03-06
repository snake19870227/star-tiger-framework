drop table if exists sys_dept;
create table sys_dept
(
    dept_flow     varchar(32) not null comment '部门流水号',
    org_flow      varchar(32) not null comment '机构流水号',
    dept_code     varchar(50) comment '部门代码',
    dept_name     varchar(500) comment '部门名称',
    record_status varchar(2) default 'Y' comment '记录状态',
    primary key (dept_flow)
);