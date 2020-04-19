package cn.niko.wink.auth.api.converter;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Map;

/**
 * @author niko.pan
 * @version 1.0.0
 * @since 2020/4/19 20:52
 */
//@Component
public class JwtTokenConverter extends JwtAccessTokenConverter {



    @Override
    public Map<String, ?> convertAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
        return super.convertAccessToken(token, authentication);
    }
}
