package com.manageapp.manageappapi.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

// Essa anotação transforma a classe em controle
@RestController // está avisando ao Spring que esta classe receberá requisições (pedidos)
public class HelloController {

    @GetMapping // diz: quando alguém acessar, execute esse método
    public String hello() {
        return "Olá, ManageApp!";
    }

}
