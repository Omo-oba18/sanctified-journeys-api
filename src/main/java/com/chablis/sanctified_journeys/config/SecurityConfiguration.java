package com.chablis.sanctified_journeys.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(
                        (csrf) -> csrf.ignoringRequestMatchers("/sanctified-journeys/**")
                                .ignoringRequestMatchers("/public/**")
                                .ignoringRequestMatchers("/api/v1/**")
                )
                .authorizeHttpRequests(
                        (requests) -> requests
                                .requestMatchers("/dashboard").authenticated()
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .requestMatchers("/displayProfile").authenticated()
                                .requestMatchers("/updateProfile").authenticated()
                                .requestMatchers("/apartments").authenticated()
                                .requestMatchers(HttpMethod.GET,"/churches/**").authenticated()
                                .requestMatchers(HttpMethod.POST,"/churches").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT,"/churches/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE,"/churches/**").hasRole("ADMIN")
                                .requestMatchers("/apartments/search").authenticated()
                                .requestMatchers("/user/**").hasRole("USER")
                                .requestMatchers("/manager/**").hasRole("MANAGER")
                                .requestMatchers("", "/", "/home").permitAll()
                                .requestMatchers("/contact").permitAll()
                                .requestMatchers("/about").permitAll()
                                .requestMatchers("/assets/**").permitAll()
                                .requestMatchers("/login").permitAll()
                                .requestMatchers("/register").permitAll()
                                .requestMatchers("/logout").permitAll()
                                .requestMatchers("/api/v1/**").permitAll()
                )
                .formLogin(loginConfigurer -> loginConfigurer.loginPage("/login")
                        .defaultSuccessUrl("/dashboard").failureUrl("/login?error=true").permitAll())
                .logout(logoutConfigurer -> logoutConfigurer.logoutSuccessUrl("/login?logout=true")
                        .invalidateHttpSession(true).permitAll())
                .httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }
}
