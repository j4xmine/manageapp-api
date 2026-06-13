package com.manageapp.manageappapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProdutoRequestDTO { // represeta o que o usuário pode enviar

    @NotBlank(message = "Preencha o nome.")
    private String nome;

    @NotBlank(message = "Preencha a descrição.")
    private String descricao;

    @NotNull(message = "Informe um preço de compra.")
    @Positive(message = "Valor deve ser maior de 0.")
    private Double precoCompra;

    @NotNull(message = "Informe um preço de venda.")
    @Positive(message = "Valor deve ser maior de 0.")
    private Double precoVenda;

    @NotNull(message = "Informe a quantidade no estoque.")
    @PositiveOrZero(message = "Valor deve ser maior ou igual a 0.")
    private Integer quantidadeEstoque;
}
