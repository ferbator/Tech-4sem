package com.ferbator.shelterapi.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//http://localhost:8080/swagger-ui/index.html#/

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Shelter service Api")
                        .version("1.0.0")
                        .description("Для доступа к защищённым эндпоинтам необходимо авторизоваться через страницу логина /login. После успешной авторизации сессия будет сохранена, и Swagger UI сможет выполнять запросы, используя cookie сессии."));
    }


}
