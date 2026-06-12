package com.manageapp.manageappapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // apontando para o Spring entender que é a classe principal
public class ManageappApplication { // Shoppinh

    public static void main(String[] args) { // botão de ligar
        // .run -> gerente abrindo as portas
        SpringApplication.run(ManageappApplication.class, args); // Spring, inicie meu aplicativo
    }

}
