drop table if exists sys_menu;

create table sys_menu
(
    menu_flow        varchar(32) not null comment '菜单流水号',
    parent_menu_flow varchar(32) comment '父级菜单流水号',
    menu_level       int comment '菜单层级',
    menu_code        varchar(50) comment '菜单代码',
    menu_name        varchar(50) comment '菜单名称',
    menu_path        varchar(500) comment '菜单地址',
    menu_order       int comment '排序码',
    module_flow      varchar(32) comment '模块流水号',
    enable_flag      varchar(2) default 'Y' comment '启用标记',
    record_status    varchar(2) default 'Y' comment '记录状态',
    primary key (menu_flow)
);