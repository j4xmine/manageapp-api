package com.manageapp.manageappapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ManageAppOpenAPI { // descrição no início da página do swagger com informações e contato comigo.

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI().info(
                new Info()
                        .title("ManageApp API")
                        .description("API REST desenvolvida em Spring Boot para Gerenciamento de Produtos e Controle de Estoque.")
                        .version("1.0.0")
                        .contact(
                                new Contact()
                                        .name("Victor Rodrigues")
                                        .email("stcontato.victor@gmail.com")
                        )
        );
    }

}
