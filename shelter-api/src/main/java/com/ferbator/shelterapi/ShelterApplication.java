package com.ferbator.shelterapi;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Главный класс запуска Spring Boot приложения "shelter-service".
 * Приложение предназначено для управления приютом для животных,
 * работает с базой данных PostgreSQL через REST API и использует авторизацию Spring Security.
 */
@SpringBootApplication
public class ShelterApplication {

    public static void main(String[] args) {
        // Создаем экземпляр SpringApplication для более гибкой настройки при запуске
        SpringApplication app = new SpringApplication(ShelterApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }
}
