package com.study.spring.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

@EnableWebSecurity  // Spring Security 적용 설정
@Configuration  // Spring 설정 클래스 사용
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig {

    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final AccessDeniedHandler accessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        // 모든 OPTIONS 요청에 대해 허용
                        .requestMatchers(HttpMethod.OPTIONS, "/**")
                        .permitAll()  
                        // 설정 경로 제외 인증 없이 접근 허용
                        .requestMatchers(
                                "/api/**",
                                "/member/signup",
                                "/member/login"
                        ).permitAll()
                        .anyRequest()
                        .authenticated()
                )
                // 용자 정의 핸들러로, 인증 실패나 접근 거부 시 사용자에게 적절한 응답제공
                .exceptionHandling(exceptionHandler ->
                        exceptionHandler
                                .authenticationEntryPoint(authenticationEntryPoint) // 인증 실패 예외처리
                                .accessDeniedHandler(accessDeniedHandler)   // 권한 부족 예외 처리
                )
                .sessionManagement(securitySessionManagementConfigurer ->
                        securitySessionManagementConfigurer.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS)
                );

        return http.build();
    }

}