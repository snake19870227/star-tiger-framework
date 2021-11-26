drop table hpry_dw_partner;

create table hpry_dw_partner
(
    flow              varchar(50) constraint hpry_dw_partner_pk primary key,
    tp_code           varchar(50),
    tp_name           varchar(500),
    template_name     varchar(500)
);

comment on table hpry_dw_partner is '低温往来单位';
comment on column hpry_dw_partner.flow is '主键';
comment on column hpry_dw_partner.tp_code is '往来单位编码';
comment on column hpry_dw_partner.tp_name is '往来单位名称';
comment on column hpry_dw_partner.template_name is '模板中名称';