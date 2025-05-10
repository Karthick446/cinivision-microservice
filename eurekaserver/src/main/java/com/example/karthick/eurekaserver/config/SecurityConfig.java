package com.example.karthick.eurekaserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	 @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http
	            .csrf().disable() // Disable CSRF
	            .authorizeHttpRequests((authorize) -> authorize
	                .antMatchers("/eureka/**").permitAll() // Allow all Eureka requests
	                .anyRequest().authenticated()
	            )
	            .httpBasic(); // Basic Auth for other endpoints
	        return http.build();
	    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user")
            .password("{noop}password") // For development, use {noop}
            .roles("USER")
            .build();
        return new InMemoryUserDetailsManager(user);
    }
}
