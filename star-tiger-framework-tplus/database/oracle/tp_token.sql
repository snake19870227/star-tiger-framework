drop table tp_token;

create table tp_token
(
    flow                     varchar(50) constraint tp_token_pk primary key,
    datetime                 varchar(20),
    access_token             varchar(900),
    expires_in               number(10),
    expires_datetime         varchar(20),
    refresh_token            varchar(900),
    refresh_expires_in       number(10),
    refresh_expires_datetime varchar(20),
    scope                    varchar(50),
    user_id                  varchar(50),
    user_name                varchar(99),
    org_id                   varchar(50),
    app_name                 varchar(50),
    sid                      varchar(50),
    user_auth_permanent_code varchar(200)
);

comment on table tp_token is 'T+操作token';
comment on column tp_token.flow is '主键';
comment on column tp_token.datetime is '获取时间';
comment on column tp_token.access_token is 'access_token';
comment on column tp_token.expires_in is 'expires_in';
comment on column tp_token.refresh_token is 'refresh_token';
comment on column tp_token.refresh_expires_in is 'refresh_expires_in';
comment on column tp_token.scope is 'scope';
comment on column tp_token.user_id is 'user_id';
comment on column tp_token.user_name is '用户姓名';
comment on column tp_token.org_id is 'org_id';
comment on column tp_token.app_name is 'app_name';
comment on column tp_token.sid is 'sid';
comment on column tp_token.user_auth_permanent_code is 'user_auth_permanent_code';