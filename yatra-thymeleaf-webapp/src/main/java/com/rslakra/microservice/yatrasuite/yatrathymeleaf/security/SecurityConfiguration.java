package com.rslakra.microservice.yatrasuite.yatrathymeleaf.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final UserDetailsService userDetailsService;

    /**
     * @param userDetailsService
     */
    public SecurityConfiguration(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .userDetailsService(userDetailsService)
            .authorizeRequests()
            .antMatchers("/css/**", "/images/**", "/js/**", "/favicon.ico").permitAll()
            .antMatchers("/login/**", "/register").permitAll()
            .antMatchers("/h2").permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .formLogin()
            .loginPage("/login")
//            .usernameParameter("email")
            .permitAll()
            .defaultSuccessUrl("/index")
//            .successForwardUrl("/index")
            .and()
            .logout()
            .permitAll()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/login");

        return http.build();
    }
}
