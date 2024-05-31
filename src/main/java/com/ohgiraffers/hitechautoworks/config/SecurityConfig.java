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
        return web->web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                .requestMatchers(new AntPathRequestMatcher("/assets/**"));
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests( auth -> {
            auth.requestMatchers("/main","/","/error/*","/auth/idCheck","/auth/searchForId","/auth/searchForPW","/member/login","/member/regist","/member/terms","/certified/loading","/certified/checkinfo").permitAll();
            auth.requestMatchers("/selectRes").hasAnyAuthority(UserRole.CUSTOMER.getRole(),UserRole.ADMIN.getRole(),UserRole.EMPLOYEE.getRole());
            auth.requestMatchers("/resCar").hasAnyAuthority(UserRole.CUSTOMER.getRole(),UserRole.ADMIN.getRole(),UserRole.EMPLOYEE.getRole());
            auth.requestMatchers("/carSubmit").hasAnyAuthority(UserRole.CUSTOMER.getRole(),UserRole.ADMIN.getRole(),UserRole.EMPLOYEE.getRole());
            auth.requestMatchers("/rescustomer").hasAnyAuthority(UserRole.CUSTOMER.getRole(),UserRole.ADMIN.getRole(),UserRole.EMPLOYEE.getRole());
            auth.requestMatchers("/resdetail").hasAnyAuthority(UserRole.CUSTOMER.getRole(),UserRole.ADMIN.getRole(),UserRole.EMPLOYEE.getRole());
            auth.requestMatchers("/contact").hasAnyAuthority(UserRole.CUSTOMER.getRole(),UserRole.ADMIN.getRole(),UserRole.EMPLOYEE.getRole());
            auth.requestMatchers("/mypage").hasAnyAuthority(UserRole.CUSTOMER.getRole(),UserRole.ADMIN.getRole(),UserRole.EMPLOYEE.getRole());
            auth.requestMatchers("/showprofile").hasAnyAuthority(UserRole.ADMIN.getRole(),UserRole.EMPLOYEE.getRole(),UserRole.CUSTOMER.getRole());
            auth.requestMatchers("/user/mainpage").hasAnyAuthority(UserRole.EMPLOYEE.getRole(), UserRole.ADMIN.getRole());
            auth.requestMatchers("/user/partAllCall").hasAnyAuthority(UserRole.EMPLOYEE.getRole(), UserRole.ADMIN.getRole());
            auth.requestMatchers("/user/rescheck").hasAnyAuthority(UserRole.EMPLOYEE.getRole(), UserRole.ADMIN.getRole());
            auth.requestMatchers("/user/repair").hasAnyAuthority(UserRole.EMPLOYEE.getRole(), UserRole.ADMIN.getRole());
            auth.requestMatchers("/user/employeePage").hasAnyAuthority(UserRole.EMPLOYEE.getRole(), UserRole.ADMIN.getRole());
            auth.requestMatchers("/user/contactList").hasAnyAuthority(UserRole.EMPLOYEE.getRole(), UserRole.ADMIN.getRole());
            auth.requestMatchers("/user/**").hasAnyAuthority(UserRole.EMPLOYEE.getRole(),UserRole.ADMIN.getRole(),UserRole.CUSTOMER.getRole());
            auth.requestMatchers("/certified/**").hasAnyAuthority(UserRole.CERTIFIED.getRole());
            auth.requestMatchers("/*").hasAnyAuthority(UserRole.ADMIN.getRole());
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
