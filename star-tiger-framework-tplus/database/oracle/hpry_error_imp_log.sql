drop table hpry_error_imp_log;

create table hpry_error_imp_log
(
    flow         varchar(50) constraint hpry_error_imp_log_pk primary key,
    type         int,
    filename     varchar(50),
    imp_content  clob,
    error_stack  clob
);