package com.rslakra.logistics.yatrasuite.yatrathymeleaf.security;

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
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/css/**", "/images/**", "/js/**", "/favicon.ico").permitAll()
                .requestMatchers("/.well-known/**").permitAll()
                .requestMatchers("/login/**", "/register").permitAll()
                .requestMatchers("/h2").permitAll()
                .requestMatchers("/error").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
            .loginPage("/login")
//                .usernameParameter("email")
            .permitAll()
                .defaultSuccessUrl("/index", true)  // true = always redirect to /index, ignore saved requests
//                .successForwardUrl("/index")
            )
            .logout(logout -> logout
            .permitAll()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
            );

        return http.build();
    }
}
