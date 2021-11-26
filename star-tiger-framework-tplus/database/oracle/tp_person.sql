drop table tp_person;

create table tp_person
(
    flow         varchar(50) constraint tp_person_pk primary key,
    tp_id        varchar(50),
    tp_code      varchar(50),
    tp_name      varchar(99),
    tp_dept_id   varchar(50),
    tp_dept_code varchar(50),
    tp_dept_name varchar(99)
);

comment on table tp_person is 'T+员工';
comment on column tp_person.flow is '主键';
comment on column tp_person.tp_id is 'T+ID';
comment on column tp_person.tp_code is '员工编码';
comment on column tp_person.tp_name is '员工名称';
comment on column tp_person.tp_dept_id is 'T+员工部门ID';
comment on column tp_person.tp_dept_code is '员工部门编码';
comment on column tp_person.tp_dept_name is '员工部门名称';