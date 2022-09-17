package com.gotogether.gotogethersbe.config;

import com.gotogether.gotogethersbe.config.auth.JwtAccessDeniedHandler;
import com.gotogether.gotogethersbe.config.auth.JwtAuthenticationEntryPoint;
import com.gotogether.gotogethersbe.config.auth.TokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final TokenManager tokenManager;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .antMatchers("/h2-console/**", "/favicon.ico")
                .antMatchers("/v2/api-docs", "/webjars/**", "/swagger-resources/**", "/swagger-ui.html", "/webjars/**", "/swagger/**");
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.csrf().disable() //csrf 토큰 막기
                .authorizeRequests() //아래 요청은 모두 허용, 이외 요청은 인증 필수
                .antMatchers("/login", "/join", "/reissue", "/logout","/products").permitAll()
                .anyRequest().authenticated()
                .and()
                .authorizeRequests() //cors설정
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .and()
                .cors().configurationSource(corsConfigurationSource())
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint) //인증 실패시 엔트리포인트 지정
                .accessDeniedHandler(jwtAccessDeniedHandler) //유효하지 않은 접근 처리
                .and()
                .sessionManagement() //기본제공 세션 막음
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and() //로그아웃 성공시 메인 페이지로 이동
                .logout().logoutUrl("/logout").logoutSuccessUrl("/")
                .and()
                .apply(new JwtSecurityConfig(tokenManager))
                .and()
                .formLogin().disable().headers().frameOptions().disable();

//                    .and()
//                .headers()
//                .frameOptions()
//                .sameOrigin() //iFrame 기반 솔루션

        return http.build();
    }

    //cors 처리
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedOriginPattern("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addExposedHeader("Authorization");
        corsConfiguration.addExposedHeader("refreshToken");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
}