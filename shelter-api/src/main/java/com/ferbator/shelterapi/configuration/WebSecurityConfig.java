package com.ferbator.shelterapi.configuration;

import com.ferbator.shelterapi.dao.enums.Role;
import com.ferbator.shelterapi.services.WebSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Конфигурация безопасности приложения.
 * Использует WebSecurityConfigurerAdapter (доступно в Spring Boot 2.6.x).
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final WebSecurityService service;

    @Autowired
    public WebSecurityConfig(WebSecurityService service) {
        this.service = service;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // Отключаем CSRF для упрощения работы с REST
                .csrf().disable()
                .authorizeRequests()
                // Разрешаем доступ к странице регистрации без авторизации
                .antMatchers("/registration").permitAll()
                //.antMatchers( "/api/owner/**").hasRole(Role.USER.name())
                //.antMatchers("/api/**").hasRole(Role.ADMIN.name())
                // Доступ к корню, к Swagger UI и документации OpenAPI для всех
                .antMatchers("/", "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                // Остальные запросы требуют аутентификации
                .anyRequest().authenticated()
                .and()
                // Настройка формы логина
                .formLogin()
                .defaultSuccessUrl("/", true)
                .permitAll()
                .and()
                // Настройка логаута
                .logout()
                .logoutSuccessUrl("/")
                .permitAll();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(service);
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
