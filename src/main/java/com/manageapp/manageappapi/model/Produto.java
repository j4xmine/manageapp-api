package com.manageapp.manageappapi.model;

import com.manageapp.manageappapi.dto.ProdutoRequestDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "produtos") // quando eu salvar um Produto, salve-o na COLEÇÃO (no BD) "produtos
public class Produto { // representa o Banco
    @Id
    private String id;
// BEAN VALIDATION
//    @NotBlank(message = "Preencha o nome.")
    private String nome;
//    @NotBlank(message = "Preencha a descrição.")
    private String descricao;
//    @Positive(message = "Valor deve ser maior que 0.")
    private Double precoCompra;
//    @Positive(message = "Valor deve ser maior de 0.")
    private Double precoVenda;
//    @PositiveOrZero(message = "Valor deve ser maior ou igual a 0.")
    private Integer quantidadeEstoque;

    private LocalDateTime dataCriacao;


}
