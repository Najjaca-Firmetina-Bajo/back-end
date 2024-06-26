package com.nfb.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/*
konfiguracija omogucava generisanje metapodataka API-ju, poput vlasnika, uslova koriscenja, licence i sl.
moguce je konfigurisati i vise servera
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key", createAPIKeyScheme()))
                .info(new Info()
                        .title("Medical Application")
                        .version("1.0.0")
                        .description("An application for medical equipment")
                        .contact(new Contact().name("Ivan Mikic").email("mikic.ra44.2020@uns.ac.rs"))
                        .license(new License().name("Your License").url("http://your-license-url.com")))
                .servers(new ArrayList<>(
                        List.of(new Server().url("http://localhost:8080").description("Local Server")))) // Add your servers
                .addSecurityItem(new SecurityRequirement().addList("bearer-key", new ArrayList<>()));
    }
    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }
}