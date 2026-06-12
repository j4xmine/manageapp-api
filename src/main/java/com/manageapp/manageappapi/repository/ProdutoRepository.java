package com.manageapp.manageappapi.repository;

import com.manageapp.manageappapi.model.Produto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ProdutoRepository extends MongoRepository<Produto, String> { // aqui iremos criar a relação com o banco de dados
// aqui faz apenas a função de CRUD

    List<Produto> findByNomeContainingIgnoreCase(String nome);
    List<Produto> findByPrecoVendaGreaterThan(Double preco);
    List<Produto> findByPrecoVendaBetween(Double min, Double max);
    List<Produto> findByDescricaoContainingIgnoreCase(String nome);
    List<Produto> findByQuantidadeEstoqueGreaterThanEqual(Integer num);
    List<Produto> findByQuantidadeEstoqueEquals(Integer num);
}
