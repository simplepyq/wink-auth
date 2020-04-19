create table t_oauth_code(
    id bigint auto_increment primary key ,
    code varchar(256),
    authentication blob,
    enabled int default 1,
    cdate datetime,
    creator varchar(32),
    edate datetime,
    editor varchar(32)
);

create unique index UK_OAUTH_CODE on t_oauth_code(code);
create index IDX_OAUTH_CODE on t_oauth_code(code);