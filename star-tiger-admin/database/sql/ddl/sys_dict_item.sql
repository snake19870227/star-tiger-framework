drop table if exists sys_dict_item;

create table sys_dict_item
(
    dict_item_flow varchar(50) not null comment '字典值流水号',
    dict_flow      varchar(50) not null comment '字典项目流水号',
    dict_item_code varchar(50) not null comment '字典值编码',
    dict_item_name varchar(50) not null comment '字典值名称',
    record_status  varchar(2) default 'Y' comment '记录状态',
    primary key (dict_item_flow)
);