drop table tp_partner;

create table tp_partner
(
    flow              varchar(50) constraint tp_partner_pk primary key,
    tp_code           varchar(50),
    tp_name           varchar(500),
    partner_type_name varchar(99)
);

comment on table tp_partner is 'T+往来单位';
comment on column tp_partner.flow is '主键';
comment on column tp_partner.tp_code is '往来单位编码';
comment on column tp_partner.tp_name is '往来单位名称';
comment on column tp_partner.partner_type_name is '往来单位类型';