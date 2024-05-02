package com.ohgiraffers.hitechautoworks.config;

import com.ohgiraffers.hitechautoworks.common.UserRole;
import com.ohgiraffers.hitechautoworks.config.handler.AuthFailHandler;
import com.ohgiraffers.hitechautoworks.config.handler.SuccessHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private AuthFailHandler authFailHandler;

    private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests( auth -> {
            auth.requestMatchers("/main","/","/auth/idCheck","/member/login","/member/regist","/certified/loading").permitAll();
            auth.requestMatchers("/*").hasAnyAuthority(UserRole.ADMIN.getRole());
            auth.requestMatchers("/employee/*").hasAnyAuthority(UserRole.EMPLOYEE.getRole());
            auth.requestMatchers("/customer/*").hasAnyAuthority(UserRole.CUSTOMER.getRole());
            auth.requestMatchers("/certified/*").hasAnyAuthority(UserRole.CERTIFIED.getRole());
            auth.anyRequest().authenticated();
        }).formLogin(login -> {
            login.loginPage("/member/login");
            login.usernameParameter("user12");
            login.passwordParameter("pass12");
            login.successHandler(new SuccessHandler());
            login.failureHandler(authFailHandler);
//            login.defaultSuccessUrl("/user/dashboard",true);
        }).logout( logout -> {
            logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
            logout.deleteCookies("JSESSIONID");
            logout.invalidateHttpSession(true);
            logout.logoutSuccessUrl("/");
        }).sessionManagement(session -> {
            session.maximumSessions(10);
            session.invalidSessionUrl("/");
        }).rememberMe(remember -> {
            remember.tokenValiditySeconds(60 * 60 * 24);
            remember.key("mykey");
        }).csrf( csrf -> csrf.disable());

        return http.build();
    }
}
