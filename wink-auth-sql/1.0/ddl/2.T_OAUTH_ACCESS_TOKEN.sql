CREATE TABLE t_oauth_access_token
(
    id                bigint auto_increment primary key not null,
    token_id          varchar(256),
    token             blob,
    authentication_id varchar(256),
    user_name         varchar(256),
    client_id         varchar(256),
    authentication    blob,
    refresh_token     varchar(256),
    enabled           int default 1                     not null,
    cdate             datetime                          not null,
    creator           VARCHAR(32)                       not null,
    edate             datetime                          not null,
    editor            VARCHAR(32)                       not null
);

create unique index UK_OAUTH_ACCESS_TOKEN on t_oauth_access_token (id);
create index IDX_AUTHENTICATION_ID on t_oauth_access_token (authentication_id);
create index IDX_OAUTH_TOKEN_ID on t_oauth_access_token (token_id);
create index IDX_OAUTH_USER_NAME ON t_oauth_access_token (USER_NAME);
create index IDX_OAUTH_TOKEN_CLIENT_ID ON t_oauth_access_token (CLIENT_ID);