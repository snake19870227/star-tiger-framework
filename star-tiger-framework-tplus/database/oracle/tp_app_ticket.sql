drop table tp_app_ticket;

create table tp_app_ticket
(
    flow          varchar(50) constraint tp_app_ticket_pk primary key,
    app_ticket    varchar(99),
    create_time   varchar(20),
    modify_time   varchar(20)
);

comment on table tp_app_ticket is 'T+应用APP_TICKET记录';
comment on column tp_app_ticket.flow is '主键';
comment on column tp_app_ticket.app_ticket is 'APP_TICKET';