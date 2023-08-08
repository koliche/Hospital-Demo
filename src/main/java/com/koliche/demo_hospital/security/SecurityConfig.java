package com.koliche.demo_hospital.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/user/**").hasRole("User")
                                .requestMatchers("/webjar/**").permitAll()
                                .requestMatchers("/admin/**").hasRole("Admin")
                                .anyRequest().authenticated()
                )
                .formLogin(log ->log.loginPage("/login"));
                httpSecurity.exceptionHandling(hand -> hand.accessDeniedPage("/notAuthorized"));
        return httpSecurity.build();
    }


    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        return new InMemoryUserDetailsManager(
                User.withUsername("user1").password(passwordEncoder.encode("1234")).roles("User").build(),
                User.withUsername("user2").password(passwordEncoder.encode("1234")).roles("User").build(),
                User.withUsername("admin").password(passwordEncoder.encode("1234")).roles("User","Admin").build()
        );
    }
}
