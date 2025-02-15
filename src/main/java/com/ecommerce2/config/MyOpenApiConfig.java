package com.ecommerce2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class MyOpenApiConfig {

    @Bean
    OpenAPI customOpenApi() {
        return new OpenAPI()
            .info(new Info().title("Ecommerce Authentication System").version("V1").description("This Java + Springboot + Mysql Application is responsibile for doing Authentication using JWT & Bcrypt"));
    }
}
