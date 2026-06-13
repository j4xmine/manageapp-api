package com.manageapp.manageappapi.mapper;

import com.manageapp.manageappapi.dto.ProdutoRequestDTO;
import com.manageapp.manageappapi.dto.ProdutoResponseDTO;
import com.manageapp.manageappapi.model.Produto;
import org.springframework.stereotype.Component;

@Component // o Spring injeta esse Mapper dentro do Service
public class ProdutoMapper {

    public Produto paraEntidade(ProdutoRequestDTO request) {
        Produto entidade = new Produto();

        entidade.setNome(request.getNome());
        entidade.setDescricao(request.getDescricao());
        entidade.setPrecoCompra(request.getPrecoCompra());
        entidade.setPrecoVenda(request.getPrecoVenda());
        entidade.setQuantidadeEstoque(request.getQuantidadeEstoque());

        return entidade;
    }


    // para estar retornando ao usuário, é porque foi salvo no BD
    public ProdutoResponseDTO paraResponse(Produto produto) {

        ProdutoResponseDTO response = new ProdutoResponseDTO();

        response.setId(produto.getId());
        response.setNome(produto.getNome());
        response.setDescricao(produto.getDescricao());
        response.setPrecoVenda(produto.getPrecoVenda());
        response.setQuantidadeEstoque(produto.getQuantidadeEstoque());
        response.setLucro(produto.getPrecoVenda() - produto.getPrecoCompra());

        return response;
    }

}
