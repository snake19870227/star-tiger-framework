drop table tp_inventory;

create table tp_inventory
(
    flow                  varchar(50) constraint tp_inventory_pk primary key,
    tp_id                 varchar(50),
    tp_code               varchar(50),
    tp_name               varchar(99),
    inventory_class_code  varchar(99),
    inventory_class_name  varchar(99),
    unit_code             varchar(99),
    unit_name             varchar(99),
    base_unit_code        varchar(99),
    base_unit_name        varchar(99),
    unit_purchase_code    varchar(99),
    unit_purchase_name    varchar(99),
    unit_sale_code        varchar(99),
    unit_sale_name        varchar(99),
    unit_stock_code       varchar(99),
    unit_stock_name       varchar(99),
    unit_retail_code      varchar(99),
    unit_retail_name      varchar(99),
    unit_manufacture_code varchar(99),
    unit_manufacture_name varchar(99),
    tp_disabled           varchar(50)
);

comment on table tp_inventory is 'T+存货';
comment on column tp_inventory.flow is '主键';
comment on column tp_inventory.tp_id is 'T+ID';
comment on column tp_inventory.tp_code is '存货编码';
comment on column tp_inventory.tp_name is '存货名称';
comment on column tp_inventory.inventory_class_code is '存货分类';
comment on column tp_inventory.inventory_class_name is '存货分类';
comment on column tp_inventory.unit_code is '计量单位';
comment on column tp_inventory.unit_name is '计量单位';
comment on column tp_inventory.base_unit_code is '主计量单位';
comment on column tp_inventory.base_unit_name is '主计量单位';
comment on column tp_inventory.unit_purchase_code is '采购常用单位';
comment on column tp_inventory.unit_purchase_name is '采购常用单位';
comment on column tp_inventory.unit_sale_code is '销售常用单位';
comment on column tp_inventory.unit_sale_name is '销售常用单位';
comment on column tp_inventory.unit_stock_code is '库存常用单位';
comment on column tp_inventory.unit_stock_name is '库存常用单位';
comment on column tp_inventory.unit_retail_code is '零售常用单位';
comment on column tp_inventory.unit_retail_name is '零售常用单位';
comment on column tp_inventory.unit_manufacture_code is '生产常用单位';
comment on column tp_inventory.unit_manufacture_name is '生产常用单位';