CREATE TABLE t_oauth_resources
(
    id            bigint auto_increment primary key,
    resource_name varchar(256),
    public_key    varchar(4000),
    private_key   varchar(4000),
    enabled       int,
    cdate         datetime    not null,
    creator       VARCHAR(32) not null,
    edate         datetime    not null,
    editor        VARCHAR(32) not null
);
create index IDX_OAUTH_RESOURCE_NAME on t_oauth_resources (resource_name);