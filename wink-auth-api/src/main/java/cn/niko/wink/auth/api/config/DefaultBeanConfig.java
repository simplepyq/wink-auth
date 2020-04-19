package cn.niko.wink.auth.api.config;

import cn.niko.wink.auth.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;
import java.util.Collections;

/**
 * @author niko.pan
 * @version 1.0.0
 * @since 2020/4/18 11:44
 */
@Configuration
public class DefaultBeanConfig {

    private static final String SIGNING_KEY = "uaa123";

    @Autowired
    private DataSource dataSource;

    @Autowired
    private AuthTokenEnhancer authTokenEnhancer;

    @Bean
    OAuth2RequestFactory defaultOAuth2RequestFactory() {
        DefaultOAuth2RequestFactory oauth2RequestFactory = new DefaultOAuth2RequestFactory(jdbcClientDetailsService(dataSource));
        return oauth2RequestFactory;
    }

    @Bean
    public JdbcClientDetailsService jdbcClientDetailsService(DataSource dataSource) {
        JdbcClientDetailsService jdbcClientDetailsService = new JdbcClientDetailsService(dataSource);
        jdbcClientDetailsService.setDeleteClientDetailsSql(Constants.DELETE_CLIENT_DETAILS_SQL);
        jdbcClientDetailsService.setFindClientDetailsSql(Constants.FIND_CLIENT_DETAILS_SQL);
        jdbcClientDetailsService.setUpdateClientDetailsSql(Constants.UPDATE_CLIENT_DETAILS_SQL);
        jdbcClientDetailsService.setUpdateClientSecretSql(Constants.UPDATE_CLIENT_SECRET_SQL);
        jdbcClientDetailsService.setInsertClientDetailsSql(Constants.INSERT_CLIENT_DETAILS_SQL);
        jdbcClientDetailsService.setSelectClientDetailsSql(Constants.SELECT_CLIENT_DETAILS_SQL);
        return jdbcClientDetailsService;
    }


    @Bean
    public AuthorizationServerTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(jwtTokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        authTokenEnhancer.setTokenEnhancers(Collections.singletonList(jwtAccessTokenConverter()));
        defaultTokenServices.setTokenEnhancer(authTokenEnhancer);
        defaultTokenServices.setClientDetailsService(jdbcClientDetailsService(dataSource));
        return defaultTokenServices;
    }

    @Bean
    public JwtTokenStore jwtTokenStore(){
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource){
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setUserExistsSql(Constants.USER_EXISTS_SQL);
        jdbcUserDetailsManager.setChangePasswordSql(Constants.CHANGE_PASSWORD_SQL);
        jdbcUserDetailsManager.setCreateUserSql(Constants.CREATE_USER_SQL);
        jdbcUserDetailsManager.setDeleteUserSql(Constants.DELETE_USER_SQL);
        jdbcUserDetailsManager.setUpdateUserSql(Constants.UPDATE_SQL);
        jdbcUserDetailsManager.setCreateAuthoritySql(Constants.CREATE_AUTHORITY_SQL);
        jdbcUserDetailsManager.setUsersByUsernameQuery(Constants.USERS_BY_USERNAME_QUERY_SQL);
        jdbcUserDetailsManager.setDeleteUserAuthoritiesSql(Constants.DELETE_USER_AUTHORITIES_SQL);
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(Constants.AUTHORITIES_BY_USERNAME_QUERY_SQL);
        return jdbcUserDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey(SIGNING_KEY);
        return jwtAccessTokenConverter;
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(jdbcUserDetailsManager(dataSource));
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(Collections.singletonList(daoAuthenticationProvider));
    }

    @Bean
    public AuthorizationCodeServices authorizationCodeServices(){
        JdbcAuthorizationCodeServices jdbcAuthorizationCodeServices = new JdbcAuthorizationCodeServices(dataSource);
        jdbcAuthorizationCodeServices.setInsertAuthenticationSql(Constants.INSERT_AUTHORIZATION_CODE_SQL);
        jdbcAuthorizationCodeServices.setSelectAuthenticationSql(Constants.SELECT_AUTHORIZATION_CODE_SQL);
        jdbcAuthorizationCodeServices.setDeleteAuthenticationSql(Constants.DELETE_AUTHORIZATION_CODE_SQL);
        return jdbcAuthorizationCodeServices;
    }

    public JdbcTokenStore jdbcTokenStore(DataSource dataSource) {
        JdbcTokenStore jdbcTokenStore = new JdbcTokenStore(dataSource);
        jdbcTokenStore.setInsertAccessTokenSql(Constants.DEFAULT_ACCESS_TOKEN_INSERT_STATEMENT);
        jdbcTokenStore.setSelectAccessTokenSql(Constants.DEFAULT_ACCESS_TOKEN_SELECT_STATEMENT);
        jdbcTokenStore.setSelectAccessTokenAuthenticationSql(Constants.DEFAULT_ACCESS_TOKEN_AUTHENTICATION_SELECT_STATEMENT);
        jdbcTokenStore.setSelectAccessTokensFromUserNameAndClientIdSql(Constants.DEFAULT_ACCESS_TOKENS_FROM_USERNAME_AND_CLIENT_SELECT_STATEMENT);
        jdbcTokenStore.setSelectAccessTokensFromUserNameSql(Constants.DEFAULT_ACCESS_TOKENS_FROM_USERNAME_SELECT_STATEMENT);
        jdbcTokenStore.setSelectAccessTokensFromClientIdSql(Constants.DEFAULT_ACCESS_TOKENS_FROM_CLIENTID_SELECT_STATEMENT);
        jdbcTokenStore.setDeleteAccessTokenSql(Constants.DEFAULT_ACCESS_TOKEN_DELETE_STATEMENT);
        jdbcTokenStore.setSelectAccessTokenFromAuthenticationSql(Constants.DEFAULT_ACCESS_TOKEN_FROM_AUTHENTICATION_SELECT_STATEMENT);
        return jdbcTokenStore;
    }
}
