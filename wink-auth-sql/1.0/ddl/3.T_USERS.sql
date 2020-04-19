CREATE TABLE t_users
(
    id       bigint auto_increment primary key,
    username varchar(50),
    password varchar(256),
    enabled  int default 1 not null,
    cdate    datetime      not null,
    creator  VARCHAR(32)   not null,
    edate    datetime      not null,
    editor   VARCHAR(32)   not null
);

create unique index UK_USER_ID on t_users (id);
create unique index UK_USER_USERNAME on t_users (username);
