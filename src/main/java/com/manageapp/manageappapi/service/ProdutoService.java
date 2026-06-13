package com.manageapp.manageappapi.service;

import com.manageapp.manageappapi.dto.ProdutoRequestDTO;
import com.manageapp.manageappapi.dto.ProdutoResponseDTO;
import org.springframework.stereotype.Service;
import com.manageapp.manageappapi.repository.ProdutoRepository;
import com.manageapp.manageappapi.model.Produto;

import java.util.List;

import com.manageapp.manageappapi.mapper.ProdutoMapper;
import com.manageapp.manageappapi.exception.ProdutoNaoEncontradoException;


@Service
public class ProdutoService {

    private final ProdutoMapper produtoMapper;
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository, ProdutoMapper produtoMapper) {
        this.produtoRepository = produtoRepository;
        this.produtoMapper = produtoMapper;
    }

    public ProdutoResponseDTO salvar(ProdutoRequestDTO request) {
        // cria o objeto pra converter de RequestDTO para Produto (tipo aceito no banco)
        // cria um novo objeto do tipo Produto para acessar o méthodo "save" do Repository, e salva o objeto convertido "produto"
        Produto produtoSalvo = produtoRepository.save(produtoMapper.paraEntidade(request));
        // response (resposta ao usuario)
        // aqui, convertemos Produto para ResponseDTO (que será o retorno ao front)
        return produtoMapper.paraResponse(produtoSalvo);
    }

    public List<ProdutoResponseDTO> listarProdutos() {
        return produtoRepository.findAll().stream().map(produtoMapper::paraResponse).toList();
    }


    public ProdutoResponseDTO buscarPorId(String id) {
        Produto produtoEncontrado = produtoRepository.findById(id).orElseThrow( () -> new ProdutoNaoEncontradoException("Produto não encontrado com o ID " + id) );
        return produtoMapper.paraResponse(produtoEncontrado);
    }

    public void deletarProdutoPorId(String id) {
        if(!produtoRepository.existsById(id)) {
            throw new ProdutoNaoEncontradoException("Produto com ID " + id + " não encontrado para deletar.");
        }
        produtoRepository.deleteById(id);
    }

    public ProdutoResponseDTO atualizarProduto(String id, ProdutoRequestDTO request) {
        Produto produtoEncontrado = produtoRepository.findById(id).orElseThrow( () -> new ProdutoNaoEncontradoException("Produto não encontrado.") );
        produtoMapper.atualizarEntidade(produtoEncontrado, request);

        Produto produtoAtualizado = produtoRepository.save(produtoEncontrado);
        return produtoMapper.paraResponse(produtoAtualizado);
    }

    public List<ProdutoResponseDTO> buscarPorNome(String nome) {
        return produtoRepository.findByNomeContainingIgnoreCase(nome).stream().map( (entidade) -> produtoMapper.paraResponse(entidade) ).toList();
    }

    public List<ProdutoResponseDTO> buscarPorPrecoMaiorQue(Double preco) {
        return produtoRepository.findByPrecoVendaGreaterThan(preco).stream().map(produtoMapper::paraResponse).toList();
    }

    public List<ProdutoResponseDTO> buscarPrecoEntre(Double min, Double max) {
        return produtoRepository.findByPrecoVendaBetween(min, max).stream().map(produtoMapper::paraResponse).toList();
    }

    public List<ProdutoResponseDTO> buscarPorDescricao(String descricao) {
        return produtoRepository.findByDescricaoContainingIgnoreCase(descricao).stream().map(produtoMapper::paraResponse).toList();
    }

    public List<ProdutoResponseDTO> buscarPorEstoque() {
        return produtoRepository.findByQuantidadeEstoqueGreaterThanEqual(1).stream().map(produtoMapper::paraResponse).toList();
    }

    public List<ProdutoResponseDTO> produtosSemEstoque() {
        return produtoRepository.findByQuantidadeEstoqueEquals(0).stream().map(produtoMapper::paraResponse).toList();
    }


}
