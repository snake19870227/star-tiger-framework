drop table sys_user_menu;

create table sys_user_menu
(
    flow      varchar(50) constraint sys_user_menu_pk primary key,
    user_id   varchar(50),
    menu_flow varchar(50)
);