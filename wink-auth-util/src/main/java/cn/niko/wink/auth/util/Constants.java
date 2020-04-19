package cn.niko.wink.auth.util;

/**
 * @author niko.pan
 * @version 1.0.0
 * @since 2020/4/18 12:28
 */
public class Constants {

    private Constants() {
    }

    public static final String DELETE_CLIENT_DETAILS_SQL = "update t_oauth_client_details set enabled = -1, edate = now(), editor = 'system'" +
            " where client_id = ? and enabled = 1";

    public static final String FIND_CLIENT_DETAILS_SQL = "select client_id, client_secret, resource_ids, scope, authorized_grant_types, " +
            " redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove " +
            " from t_oauth_client_details where enabled = 1 order by client_id";

    public static final String UPDATE_CLIENT_DETAILS_SQL = "update t_oauth_client_details " +
            " set resource_ids = ?, scope = ?,authorized_grant_types = ?, redirect_uri = ?, authorities = ?, " +
            " access_token_validity = ?, refresh_token_validity = ?, additional_information = ?, autoapprove = ?, " +
            " edate = now(), editor = 'system' where client_id = ? and enabled = 1 ";

    public static final String UPDATE_CLIENT_SECRET_SQL = "update t_oauth_client_details set client_secret = ?, edate = now(), editor = 'system' " +
            " where client_id = ? and enabled = 1 ";

    public static final String INSERT_CLIENT_DETAILS_SQL = "insert into t_oauth_client_details " +
            " (client_secret, resource_ids, scope, authorized_grant_types, redirect_uri, authorities, access_token_validity, " +
            " refresh_token_validity, additional_information, autoapprove, client_id, enabled, cdate, creator, edate, editor) " +
            " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 1, now(), 'system', now(), 'system')";

    public static final String SELECT_CLIENT_DETAILS_SQL = "select client_id, client_secret, resource_ids, scope, authorized_grant_types, " +
            " redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove " +
            " from t_oauth_client_details where client_id = ? and enabled = 1 ";

    public static final String DEFAULT_ACCESS_TOKEN_INSERT_STATEMENT = "insert into t_oauth_access_token " +
            " (token_id, token, authentication_id, user_name, client_id, authentication, refresh_token, enabled, cdate, creator, edate, editor) " +
            " values (?, ?, ?, ?, ?, ?, ?, 1, now(), 'system', now(), 'system')";

    public static final String DEFAULT_ACCESS_TOKEN_SELECT_STATEMENT = "select token_id, token from t_oauth_access_token where token_id = ? and enabled = 1 ";

    public static final String DEFAULT_ACCESS_TOKEN_AUTHENTICATION_SELECT_STATEMENT = "select token_id, authentication from t_oauth_access_token where token_id = ? and enabled = 1";

    public static final String DEFAULT_ACCESS_TOKENS_FROM_USERNAME_AND_CLIENT_SELECT_STATEMENT = "select token_id, token from t_oauth_access_token " +
            " where user_name = ? and client_id = ? and enabled = 1 ";

    public static final String DEFAULT_ACCESS_TOKENS_FROM_USERNAME_SELECT_STATEMENT = "select token_id, token " +
            " from t_oauth_access_token where user_name = ? and enabled = 1 ";

    public static final String DEFAULT_ACCESS_TOKENS_FROM_CLIENTID_SELECT_STATEMENT = "select token_id, token " +
            " from t_oauth_access_token where client_id = ? and enabled = 1 ";

    public static final String DEFAULT_ACCESS_TOKEN_DELETE_STATEMENT = "update t_oauth_access_token set enabled = -1, edate = now(), editor = 'system' " +
            " where token_id = ? and enabled = 1 ";

    public static final String DEFAULT_ACCESS_TOKEN_FROM_AUTHENTICATION_SELECT_STATEMENT = "select t.token_id, t.token from " +
            " (select token_id, token from t_oauth_access_token where authentication_id = ? and enabled = 1 order by id desc) as t limit 2";

    public static final String USER_EXISTS_SQL = "select username from t_users where username = ?";

    public static final String CHANGE_PASSWORD_SQL = "update t_users set password = ?, edate = now(), editor = 'system' where username = ?";

    public static final String CREATE_USER_SQL = "insert into t_users (username, password, enabled, cdate, creator, edate, editor) " +
            " values (?, ?, ?, now(),'system', now(), 'system') ";

    public static final String DELETE_USER_SQL = "delete from t_users where username = ?";

    public static final String UPDATE_SQL = "update t_users set password = ?, enabled = ?, edate = now(), editor = 'system' where username = ?";

    public static final String CREATE_AUTHORITY_SQL = "insert into t_authorities (username, authority, cdate, creator, edate, editor) " +
            " values (?, ?, now(), 'system', now(), 'system') ";

    public static final String DELETE_USER_AUTHORITIES_SQL = "delete from t_authorities where username = ?";

    public static final String USERS_BY_USERNAME_QUERY_SQL = "select username,password,enabled from t_users where username = ?";

    public static final String AUTHORITIES_BY_USERNAME_QUERY_SQL = "select username,authority from t_authorities where username = ?";

    public static final String INSERT_AUTHORIZATION_CODE_SQL = "insert into t_oauth_code (code, authentication, enabled, cdate, creator, edate, editor) values (?, ?, 1, now(), 'system', now(), 'system')";

    public static final String SELECT_AUTHORIZATION_CODE_SQL = "select code, authentication from t_oauth_code where code = ? and enabled = 1";

    public static final String DELETE_AUTHORIZATION_CODE_SQL = "update t_oauth_code set enabled = -1, edate = now(), editor = 'system' where code = ?";
}
