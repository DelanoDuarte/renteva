package com.app.renteva.shared.config;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableWebSecurity
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static final List<String> ALLOWED_ORIGINS = List.of("http://localhost:8090", "https://editor.swagger.io");
    public static final String[] PROTECTED_ENDPOINTS = {
            "/places/**",
            "/posts/**",
            "/rent-offer"
    };

    TokenFilter tokenFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http = http.cors()
                .configurationSource(
                        request -> {
                            var cors = new CorsConfiguration();
                            cors.setAllowedOrigins(ALLOWED_ORIGINS);
                            cors.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                            cors.setAllowedHeaders(List.of("*"));
                            return cors;
                        })
                .and()
                .csrf()
                .disable();

        // Set session management to stateless
        http = http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();

        // Set unauthorized requests exception handler
        http = http.exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                .and();

        // Set permissions on endpoints
        http.authorizeRequests()
                .antMatchers("/", "/swagger-ui/", "/swagger-ui.html", "/webjars/**", "/swagger-resources/**", "/api-docs")
                .permitAll()
                // Our public endpoints
                .antMatchers(HttpMethod.POST, "/user/sign-in", "/user/renter", "/user/owner")
                .permitAll()
                // Our private endpoints
                .antMatchers(HttpMethod.GET, PROTECTED_ENDPOINTS)
                .authenticated();

        http.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
