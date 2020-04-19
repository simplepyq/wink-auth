package cn.niko.wink.auth.api.config;

import cn.niko.wink.auth.BaseResponse;
import cn.niko.wink.auth.util.JsonUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Log4j2
public class AuthExceptionEntryPoint implements AuthenticationEntryPoint {

    private static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
        try {
            if (ANT_PATH_MATCHER.matchStart("/oauth/authorize", request.getRequestURI())) {
                response.sendRedirect("/login");
                return;
            }
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            log.info("Default Handle error message: {}", authException.getMessage());
            response.getWriter().write(JsonUtils.format(new BaseResponse("E0001", "authorized fail")));
        } catch (IOException e) {
            log.error("Default Handler exception: {}", e.getMessage());
        }
    }
}