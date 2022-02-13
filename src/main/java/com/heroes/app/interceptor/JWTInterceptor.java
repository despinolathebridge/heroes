package com.heroes.app.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.heroes.app.exception.custom.HeroeInvalidJWTException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Slf4j
@Component
public class JWTInterceptor implements HandlerInterceptor {

    private static final String PREFIX = "Bearer ";
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String SCOPE = "scope";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Optional.ofNullable(request.getHeader(AUTHORIZATION_HEADER)).ifPresentOrElse(
                value -> {
                    try {
                        DecodedJWT jwt = JWT.decode(value.split(PREFIX)[1]);
                        var scope = jwt.getClaim(SCOPE).asString();
                        request.setAttribute("scope", scope);
                    } catch (ArrayIndexOutOfBoundsException | JWTDecodeException e1) {
                        log.debug(e1.getMessage());
                        throw new HeroeInvalidJWTException();
                    } catch (Exception e3) {
                        log.debug(e3.getMessage());
                        throw new HeroeInvalidJWTException();
                    }
                }, () -> {
                    throw new HeroeInvalidJWTException();
                });
        return true;
    }
}
