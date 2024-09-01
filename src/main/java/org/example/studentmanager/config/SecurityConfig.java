package org.example.studentmanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * @author vero-git-hub
 **/
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Отключение CSRF для простоты
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/webjars/**", "/swagger-ui.html").permitAll() // Разрешить доступ к Swagger без авторизации
                        .requestMatchers("/hello").permitAll() // Разрешить доступ без авторизации к /hello
                        .anyRequest().authenticated() // Все остальные запросы требуют аутентификации
                )
                .formLogin(withDefaults()); // Включение формы авторизации по умолчанию

        return http.build();
    }
}
