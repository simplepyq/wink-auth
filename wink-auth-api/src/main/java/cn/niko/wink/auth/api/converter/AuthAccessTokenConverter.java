package cn.niko.wink.auth.api.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

@Component
public class AuthAccessTokenConverter extends DefaultAccessTokenConverter {

    @Autowired
    private JdbcClientDetailsService jdbcClientDetailsService;

    @Override
    public Map<String, ?> convertAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
        Map<String, Object> response = (Map<String, Object>) super.convertAccessToken(token, authentication);
        //Add scope to check token result
        BaseClientDetails clientDetails = (BaseClientDetails) jdbcClientDetailsService.loadClientByClientId(authentication.getName());
        long exp = (long) response.get(AccessTokenConverter.EXP);
        LocalDateTime localDateTime = new Date(exp * 1000).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        response.put(AccessTokenConverter.EXP, localDateTime);
        response.put("scope", clientDetails.getScope());
        response.remove("authorities");
        response.remove("additional_information");
        return response;
    }
}
