package com.manageapp.manageappapi.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "vendas")
public class Venda {

    @Id
    private String id;

    private Produto produto;

    private Integer quantidadeVendida;

    private Double valorTotalVenda;

    private Double valorUnitarioVenda;

    private LocalDateTime dataVenda;

}
