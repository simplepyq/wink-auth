CREATE TABLE t_authorities
(
    id        bigint auto_increment primary key,
    username  varchar(50),
    authority varchar(50),
    cdate     DATETIME    not null,
    creator   VARCHAR(32) not null,
    edate     DATETIME    not null,
    editor    VARCHAR(32) not null
);


create unique index UK_AUTH_USERNAME on t_authorities (username);

