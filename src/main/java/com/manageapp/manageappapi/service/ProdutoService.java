package com.manageapp.manageappapi.service;

import com.manageapp.manageappapi.dto.ProdutoRequestDTO;
import com.manageapp.manageappapi.dto.ProdutoResponseDTO;
import org.springframework.stereotype.Service;
import com.manageapp.manageappapi.repository.ProdutoRepository;
import com.manageapp.manageappapi.model.Produto;

import java.util.ArrayList;
import java.util.List;

import com.manageapp.manageappapi.exception.ProdutoNaoEncontradoException;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    // MÉTODO GOSTOSO anti repetição
    private ProdutoResponseDTO converterParaResponse(Produto produto) {
        ProdutoResponseDTO response = new ProdutoResponseDTO();
        response.setId(produto.getId());
        response.setNome(produto.getNome());
        response.setDescricao(produto.getDescricao());
        response.setPrecoVenda(produto.getPrecoVenda());
        response.setQuantidadeEstoque(produto.getQuantidadeEstoque());
        response.setLucro(produto.getPrecoVenda() - produto.getPrecoCompra());

        return response;
    }



    public ProdutoResponseDTO salvar(ProdutoRequestDTO request) {
        // cria o objeto pra converter de RequestDTO para Produto (tipo aceito no banco)
        Produto produto = new Produto();
        produto.setNome(request.getNome());
        produto.setDescricao(request.getDescricao());
        produto.setPrecoCompra(request.getPrecoCompra());
        produto.setPrecoVenda(request.getPrecoVenda());
        produto.setQuantidadeEstoque(request.getQuantidadeEstoque());

        // cria um novo objeto do tipo Produto para acessar o método "save" do Repository, e salva o objeto convertido "produto"
        Produto produtoSalvo = produtoRepository.save(produto);

        // response (resposta ao usuario)
        // aqui, convertemos Produto para ResponseDTO (que será o retorno ao front)
        ProdutoResponseDTO response = new ProdutoResponseDTO();
        response.setId(produtoSalvo.getId());
        response.setNome(produtoSalvo.getNome());
        response.setDescricao(produtoSalvo.getDescricao());
        response.setPrecoVenda(produtoSalvo.getPrecoVenda());
        response.setQuantidadeEstoque(produtoSalvo.getQuantidadeEstoque());
        response.setLucro(
                produtoSalvo.getPrecoVenda() - produtoSalvo.getPrecoCompra()
        );

        return response;
    }

    public List<ProdutoResponseDTO> listarProdutos() {
//        List<Produto> produtos = produtoRepository.findAll();
//        List<ProdutoResponseDTO> response = new ArrayList<>();
//        for(Produto p : produtos) {
//            response.add(converterParaResponse(p));
//        }

        // ***
        List<ProdutoResponseDTO> response = produtoRepository.findAll().stream().map( this::converterParaResponse ).toList();
        // produtoRepository.findAll() -> retorna um List<Produto>
        // stream() -> transforma num fluxo (como se fosse um forEach) produto, produto, produto...
        // .map(this::méthod) -> aponta para cada objeto Produto pelo o qual o stream está percorrendo, logo em seguida "::" chamando o méthod para converter Produto (entidade) para ProdutoDTO
        // toList() -> converte para um tipo "List"


        return response;
    }


    public ProdutoResponseDTO buscarPorId(String id) {
        Produto produtoEncontrado = produtoRepository.findById(id).orElseThrow( () -> new ProdutoNaoEncontradoException("Produto não encontrado com o ID " + id) );

        ProdutoResponseDTO response = new ProdutoResponseDTO();
        response.setId(produtoEncontrado.getId());
        response.setNome(produtoEncontrado.getNome());
        response.setDescricao(produtoEncontrado.getDescricao());
        response.setPrecoVenda(produtoEncontrado.getPrecoVenda());
        response.setQuantidadeEstoque(produtoEncontrado.getQuantidadeEstoque());
        response.setLucro(produtoEncontrado.getPrecoVenda() - produtoEncontrado.getPrecoCompra());

        return response;
    }



    public void deletarProdutoPorId(String id) {
        if(!produtoRepository.existsById(id)) {
            throw new ProdutoNaoEncontradoException("Produto com ID " + id + " não encontrado para deletar.");
        }
        produtoRepository.deleteById(id);
    }

    public ProdutoResponseDTO atualizarProduto(String id, ProdutoRequestDTO request) {
        Produto produtoExistente = produtoRepository.findById(id).orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado para atualizar."));
        produtoExistente.setNome(request.getNome());
        produtoExistente.setDescricao(request.getDescricao());
        produtoExistente.setPrecoCompra(request.getPrecoCompra());
        produtoExistente.setPrecoVenda(request.getPrecoVenda());
        produtoExistente.setQuantidadeEstoque(request.getQuantidadeEstoque());

        Produto produtoSalvo = produtoRepository.save(produtoExistente); // salvando a entidade no BD

        // convertendo para ResponseDTO para retornar justamente o DTO dito no tipo de retorno
        ProdutoResponseDTO response = new ProdutoResponseDTO();
        response.setId(produtoSalvo.getId());
        response.setNome(produtoSalvo.getNome());
        response.setDescricao(produtoSalvo.getDescricao());
        response.setPrecoVenda(produtoSalvo.getPrecoVenda());
        response.setQuantidadeEstoque(produtoSalvo.getQuantidadeEstoque());
        response.setLucro(produtoSalvo.getPrecoVenda() - produtoSalvo.getPrecoCompra());

        return response;
    }

    public List<ProdutoResponseDTO> buscarPorNome(String nome) {
//        List<Produto> produtosEncontrados = produtoRepository.findByNomeContainingIgnoreCase(nome);
//        List<ProdutoResponseDTO> response = new ArrayList<>();
//        for(Produto p : produtosEncontrados) {
//            response.add(converterParaResponse(p));
//        }
        // ***
        return produtoRepository.findByNomeContainingIgnoreCase(nome).stream().map(this::converterParaResponse).toList();
    }

    public List<ProdutoResponseDTO> buscarPorPrecoMaiorQue(Double preco) {
        return produtoRepository.findByPrecoVendaGreaterThan(preco).stream().map(this::converterParaResponse).toList();
    }

    public List<ProdutoResponseDTO> buscarPrecoEntre(Double min, Double max) {
        return produtoRepository.findByPrecoVendaBetween(min, max).stream().map(this::converterParaResponse).toList();
    }

    public List<ProdutoResponseDTO> buscarPorDescricao(String descricao) {
        return produtoRepository.findByDescricaoContainingIgnoreCase(descricao).stream().map(this::converterParaResponse).toList();
    }

    public List<ProdutoResponseDTO> buscarPorEstoque() {
        return produtoRepository.findByQuantidadeEstoqueGreaterThanEqual(1).stream().map(this::converterParaResponse).toList();
    }

    public List<ProdutoResponseDTO> produtosSemEstoque() {
        return produtoRepository.findByQuantidadeEstoqueEquals(0).stream().map(this::converterParaResponse).toList();
    }


}
