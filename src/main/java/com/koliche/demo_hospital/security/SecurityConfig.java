package com.koliche.demo_hospital.security;


import com.koliche.demo_hospital.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/").hasRole("User")
                                .requestMatchers("/admin/**").hasRole("Admin")
                                .requestMatchers("/user/**").hasRole("User")
                                .anyRequest().authenticated()
                )
                .formLogin(login ->
                        login.loginPage("/login").defaultSuccessUrl("/").permitAll()
                )
                .exceptionHandling(handling ->
                        handling.accessDeniedPage("/notAuthorized")
                );
                httpSecurity.userDetailsService(userDetailsService);
        return httpSecurity.build();
    }



    //@Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        return new InMemoryUserDetailsManager(
                User.withUsername("user1").password(passwordEncoder.encode("1234")).roles("User").build(),
                User.withUsername("user2").password(passwordEncoder.encode("1234")).roles("User").build(),
                User.withUsername("admin").password(passwordEncoder.encode("1234")).roles("User","Admin").build()
        );
    }

    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }
}
