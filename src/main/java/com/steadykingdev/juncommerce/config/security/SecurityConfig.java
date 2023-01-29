package com.steadykingdev.juncommerce.config.security;

import com.steadykingdev.juncommerce.jwt.JwtAccessDeniedHandler;
import com.steadykingdev.juncommerce.jwt.JwtAuthenticationEntryPoint;
import com.steadykingdev.juncommerce.jwt.JwtSecurityConfig;
import com.steadykingdev.juncommerce.jwt.TokenProvider;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity // 기본적인 웹 보안 활성화
@EnableGlobalMethodSecurity(prePostEnabled = true) // @PreAuthorize를 메서드 단위로 추가하기 위해 적용
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring()
                .antMatchers(
                        "/favicon.ico",
                        "/swagger-resources/**",
                        "/swagger-ui.html",
                        "/v2/api-docs",
                        "/webjars/**"
                );
    }

    @Override
    public void configure(HttpSecurity http) throws Exception{
        http
                .httpBasic().disable() // rest api 이므로 기본설정 disable, 기본설정은 비인증시 로그인 폼 화면으로 리다이렉트된다.
                .csrf().disable() // token 방식이므로 disable

                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션을 사용하지 않기때문

                .and()
                .authorizeRequests() // HttpServletRequest를 사용하는 요청들에 대한 접근 제한을 설정
                .antMatchers("/api/member/add").permitAll()
                .antMatchers("/api/member/add/admin").permitAll()
                .antMatchers("/api/login").permitAll()
                .antMatchers("/api/item/*").permitAll()
                .anyRequest().authenticated() // 나머지 요청은 인증받아야함.

                .and()
                .apply(new JwtSecurityConfig(tokenProvider)); // JwtFilter를 addFilterBefore로 등록한 config 클래스도 적용.
    }

}
