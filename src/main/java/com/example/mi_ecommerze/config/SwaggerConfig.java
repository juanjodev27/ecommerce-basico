package com.example.mi_ecommerze.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Proyecto E-commerce basico")
                        .version("1.0.0")
                        .description("Documentacion de API REST para sistema e-commerce")
                        .contact(new Contact()
                                .name("Juan Jose Garcete")
                                .email("juanjosegarcete9@gmail.com")
                                .url("https://github.com/juanjodev27"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT"))
                );
    }
}
