package com.zeeshan.security.config;

import com.zeeshan.security.service.CustomerService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig {
    @Autowired
    private CustomerService customerService;
/**
 * AuthProvider will load the customer record from table and give to AuthenticationManager.
 * passwordEncoder() will be use to check original UI pwd will be compare from db side encrypted pwd
 * Authentication provider details are overrided here so default pwd at console will not generate now.
 */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(passwordEncoder());
        authProvider.setUserDetailsService(customerService);
        return authProvider;
    }

/**
 * AuthenticationManager is responsible to check login credentials are valid or not.
 * AuthenticationManager will authenticate user is validated or not.
 */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }

    /**
     *  BCryptPasswordEncoder will be use to encode user pwd
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(request -> {
                    request.requestMatchers("/register", "/login")
                            .permitAll()
                            .anyRequest()
                            .authenticated();
                });
        return httpSecurity.build();
    }
}
