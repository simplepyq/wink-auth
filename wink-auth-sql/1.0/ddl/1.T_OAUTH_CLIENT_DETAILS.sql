create table t_oauth_client_details
(
    id                      bigint auto_increment primary key,
    client_id               varchar(256),
    resource_ids            varchar(256),
    client_secret           varchar(256),
    scope                   varchar(2000),
    authorized_grant_types  varchar(256),
    web_server_redirect_uri varchar(256),
    authorities             varchar(256),
    access_token_validity   long,
    refresh_token_validity  long,
    additional_information  varchar(4000),
    autoapprove             varchar(256),
    enabled                 int default 1 not null,
    cdate datetime,
    creator varchar(256),
    edate datetime,
    editor varchar(256)
);

create unique index UK_OAUTH_CLIENT_ID ON t_oauth_client_details (id);
create index IDX_OAUTH_CLIENT_ID on t_oauth_client_details (client_id);