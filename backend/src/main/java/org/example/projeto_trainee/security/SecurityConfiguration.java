package org.example.projeto_trainee.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// Substituindo comportamento padrão do SpringSecurity

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // "AbstractHttpConfigurer::disable": disable default configurations of Spring Security
        http.csrf(AbstractHttpConfigurer::disable)
                // Configuring public routes
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/v1/users/login").permitAll()
                            .requestMatchers("/v1/users/signup").permitAll()
                            .requestMatchers("/v1/users/verify-email/{verificationToken}").permitAll()
                            .requestMatchers("/v1/users/forgot-password").permitAll()
                            .requestMatchers("/v1/users/forgot-password/verify").permitAll()
                            .requestMatchers("/v1/users/hello").permitAll()
                            .requestMatchers(new AntPathRequestMatcher("/v3/**")).permitAll()
                            .requestMatchers(new AntPathRequestMatcher("/swagger-ui/**")).permitAll()
                            .anyRequest().authenticated();
                }).addFilterBefore(securityFilter, BasicAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
