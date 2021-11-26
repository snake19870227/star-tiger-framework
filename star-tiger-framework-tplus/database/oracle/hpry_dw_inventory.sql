drop table hpry_dw_inventory;

create table hpry_dw_inventory
(
    flow          varchar(50) constraint hpry_dw_inventory_pk primary key,
    tp_code       varchar(50),
    tp_name       varchar(99),
    template_name varchar(500)
);

comment on table hpry_dw_inventory is '低温产品';
comment on column hpry_dw_inventory.flow is '主键';
comment on column hpry_dw_inventory.tp_code is '存货编码';
comment on column hpry_dw_inventory.tp_name is '存货名称';
comment on column hpry_dw_inventory.template_name is '模板中名称';