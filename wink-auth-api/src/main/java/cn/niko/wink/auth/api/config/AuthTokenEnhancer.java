package cn.niko.wink.auth.api.config;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * @author niko.pan
 * @version 1.0.0
 * @since 2020/4/19 21:05
 */
@Component
public class AuthTokenEnhancer extends TokenEnhancerChain {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        OAuth2AccessToken oAuth2AccessToken = super.enhance(accessToken, authentication);
        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) oAuth2AccessToken;
        token.setScope(Collections.emptySet());
        return token;
    }
}
