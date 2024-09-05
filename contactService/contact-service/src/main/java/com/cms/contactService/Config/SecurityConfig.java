package com.cms.contactService.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import  org.springframework.security.config.annotation.web.builders.*;
import org.springframework.web.client.RestTemplate;

import java.security.AllPermission;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception{
        return security.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize-> authorize
                        .requestMatchers(HttpMethod.POST).permitAll()
                        .requestMatchers(HttpMethod.DELETE).hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.PUT).hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin123"))
                .roles("ADMIN")
                .build();
        UserDetails user = User.builder()
                .username("Akanksha P")
                .password(passwordEncoder().encode("shilpa123"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin,user);
    }

}
