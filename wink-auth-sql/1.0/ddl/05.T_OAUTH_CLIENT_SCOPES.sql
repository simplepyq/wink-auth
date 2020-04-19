CREATE TABLE t_oauth_client_scopes (
  id BIGINT AUTO_INCREMENT PRIMARY KEY ,
  scope varchar(256) ,
  resource_id BIGINT ,
  uri varchar(4000),
  enabled INT,
  cdate DATETIME not null,
  creator VARCHAR(32) not null,
  edate DATETIME not null,
  editor VARCHAR(32) not null
);

create index IDX_OAUTH_SCOPE on t_oauth_client_scopes(scope);
create index IDX_OAUTH_SCOPE_RESOURCE_ID on t_oauth_client_scopes(resource_id);