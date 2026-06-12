package com.manageapp.manageappapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoResponseDTO { // tudo que o usuário vê

    private String id;
    private String nome;
    private String descricao;
    private Double precoVenda;
    private Integer quantidadeEstoque;
    private Double lucro;



}
