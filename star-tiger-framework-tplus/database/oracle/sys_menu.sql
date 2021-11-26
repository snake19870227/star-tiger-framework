drop table sys_menu;

create table sys_menu
(
    flow      varchar(50) constraint sys_menu_pk primary key,
    menu_name varchar(99),
    menu_path varchar(99)
);