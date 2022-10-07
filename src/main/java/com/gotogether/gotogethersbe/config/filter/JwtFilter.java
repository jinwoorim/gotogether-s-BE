package com.gotogether.gotogethersbe.config.filter;

import com.gotogether.gotogethersbe.config.auth.TokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";

    private final TokenManager tokenManager;
    private final RedisTemplate redisTemplate;

    // 실제 필터링 로직
    // JWT 토큰의 인증 정보를 현재 쓰레드의 SecurityContext 에 저장
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        // JWT 토큰 추출
        String jwt = resolveToken(request);
        // 토큰 유효성 확인
        if (StringUtils.hasText(jwt) && tokenManager.validateToken(jwt)) {
            // Redis에 해당 Access Token logout 여부 확인
            String isLogout = (String) redisTemplate.opsForValue().get(jwt);
            if (ObjectUtils.isEmpty(isLogout)) {
                Authentication authentication = tokenManager.getAuthentication(jwt);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

    // Request Header 에서 토큰 정보를 꺼내오기
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }
}