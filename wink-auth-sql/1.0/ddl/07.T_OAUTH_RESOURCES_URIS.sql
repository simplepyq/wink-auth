CREATE TABLE t_oauth_resources_uris
(
    id          bigint auto_increment primary key,
    uri         varchar(256),
    resource_id bigint,
    cdate       datetime    not null,
    creator     VARCHAR(32) not null,
    edate       datetime    not null,
    editor      VARCHAR(32) not null
);

create index IDX_OAUTH_URI_RESOURCE on t_oauth_resources_uris (resource_id);