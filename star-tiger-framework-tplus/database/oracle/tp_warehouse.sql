drop table tp_warehouse;

create table tp_warehouse
(
    flow    varchar(50) constraint tp_warehouse_pk primary key,
    tp_id   varchar(50),
    tp_code varchar(50),
    tp_name varchar(99)
);

comment on table tp_warehouse is 'T+仓库';
comment on column tp_warehouse.flow is '主键';
comment on column tp_warehouse.tp_id is 'T+ID';
comment on column tp_warehouse.tp_code is '仓库编码';
comment on column tp_warehouse.tp_name is '仓库名称';